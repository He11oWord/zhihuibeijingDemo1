package com.gxut.zhihuibeijingDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.utils.PrefUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 新闻详情页面
 * 
 * @author lizhao
 */
public class NewsDetailActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.acti_news_detail_ib1)
	private ImageButton acti_news_detail_ib1;
	@ViewInject(R.id.acti_news_detail_ib2)
	private ImageButton acti_news_detail_ib2;
	@ViewInject(R.id.acti_news_detail_ib3)
	private ImageButton acti_news_detail_ib3;
	@ViewInject(R.id.acti_news_detail_wv)
	private WebView acti_news_detail_wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news_detail);

		View view = View.inflate(this, R.layout.activity_news_detail, null);
		tv = (TextView) findViewById(R.id.base_activity_tv_title);
		acti_news_detail_ib1 = (ImageButton) findViewById(R.id.acti_news_detail_ib1);
		acti_news_detail_ib2 = (ImageButton) findViewById(R.id.acti_news_detail_ib2);
		acti_news_detail_ib3 = (ImageButton) findViewById(R.id.acti_news_detail_ib3);
		acti_news_detail_wv = (WebView) findViewById(R.id.acti_news_detail_wv);
		String url = getIntent().getStringExtra("url");
		String title = getIntent().getStringExtra("title");

		if (!TextUtils.isEmpty(title)) {
			tv.setText(title);
		}
		WebSettings settings = acti_news_detail_wv.getSettings();
		settings.setJavaScriptEnabled(true);// 表示支持js
		settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
		settings.setUseWideViewPort(true);// 支持双击缩放

		acti_news_detail_wv.setWebViewClient(new WebViewClient() {

			/**
			 * 网页开始加载
			 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				System.out.println("网页开始加载");
				// pbProgress.setVisibility(View.VISIBLE);
			}

			/**
			 * 网页加载结束
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				System.out.println("网页开始结束");

				// pbProgress.setVisibility(View.GONE);
			}

			/**
			 * 所有跳转的链接都会在此方法中回调
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// tel:110
				System.out.println("跳转url:" + url);
				view.loadUrl(url);

				return true;
				// return super.shouldOverrideUrlLoading(view, url);
			}
		});
		// //功能更强大的内置客户端
		// acti_news_detail_wv.setWebChromeClient(new WebChromeClient() {
		//
		// /**
		// * 进度发生变化
		// */
		// @Override
		// public void onProgressChanged(WebView view, int newProgress) {
		// System.out.println("加载进度:" + newProgress);
		// super.onProgressChanged(view, newProgress);
		// }
		//
		// /**
		// * 获取网页标题
		// */
		// @Override
		// public void onReceivedTitle(WebView view, String title) {
		// System.out.println("网页标题:" + title);
		// super.onReceivedTitle(view, title);
		// }
		// });

		// mWebView.goBack()
		acti_news_detail_wv.loadUrl(url);
		mCurrentItem = PrefUtils.getInt(NewsDetailActivity.this,
				"textsizecheck", 1);
		sitTextSize(mCurrentItem);
		acti_news_detail_ib1.setOnClickListener(this);
		acti_news_detail_ib2.setOnClickListener(this);
		acti_news_detail_ib3.setOnClickListener(this);
	}

	private int mCurrentChooseItem;// 记录当前选中的item, 点击确定前
	private int mCurrentItem;// 记录当前选中的item, 点击确定后
	private TextView tv;

	/**
	 * 显示选择对话框
	 */
	private void showChooseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		String[] items = new String[] { "超大号字体", "大号字体", "正常字体", "小号字体",
				"超小号字体" };
		builder.setTitle("字体设置");
		mCurrentItem = PrefUtils.getInt(NewsDetailActivity.this,
				"textsizecheck", 1);
		sitTextSize(mCurrentItem);
		builder.setSingleChoiceItems(items, mCurrentItem,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("选中:" + which);
						mCurrentChooseItem = which;
					}
				});

		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				sitTextSize(mCurrentChooseItem);

				// mCurrentItem = mCurrentChooseItem;
			}

		});

		builder.setNegativeButton("取消", null);

		builder.show();
	}

	/**
	 * 设置标题栏上面的点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.acti_news_detail_ib1:
			finish();
			break;
		case R.id.acti_news_detail_ib2:
			showChooseDialog();
			break;
		case R.id.acti_news_detail_ib3:
			shareApplication();
			break;
		}

	}

	/**
	 * 设置字体的大小
	 * 
	 * @param mCurrentChooseItem
	 */
	private void sitTextSize(int mCurrentChooseItem) {
		WebSettings settings = acti_news_detail_wv.getSettings();
		switch (mCurrentChooseItem) {
		case 0:
			settings.setTextSize(TextSize.LARGEST);
			PrefUtils.setInt(NewsDetailActivity.this, "textsizecheck", 0);
			break;
		case 1:
			settings.setTextSize(TextSize.LARGER);
			PrefUtils.setInt(NewsDetailActivity.this, "textsizecheck", 1);
			break;
		case 2:
			settings.setTextSize(TextSize.NORMAL);
			PrefUtils.setInt(NewsDetailActivity.this, "textsizecheck", 2);
			break;
		case 3:
			settings.setTextSize(TextSize.SMALLER);
			PrefUtils.setInt(NewsDetailActivity.this, "textsizecheck", 3);
			break;
		case 4:
			settings.setTextSize(TextSize.SMALLEST);
			PrefUtils.setInt(NewsDetailActivity.this, "textsizecheck", 4);
			break;

		default:
			break;
		}
	}
	
	/**
	 * 分享软件
	 */
	private void shareApplication() {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.SEND");
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "推荐给你一款软件，智慧北京。" );
		startActivity(intent);
	}
}
