package com.gxut.zhihuibeijingDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * ��������ҳ��
 * @author lizhao
 */
public class NewsDetailActivity extends Activity implements OnClickListener{
	
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
		//ViewUtils.inject(this, view);
		acti_news_detail_ib1 = (ImageButton) findViewById(R.id.acti_news_detail_ib1);
		acti_news_detail_ib2= (ImageButton) findViewById(R.id.acti_news_detail_ib2);
		acti_news_detail_ib3 = (ImageButton) findViewById(R.id.acti_news_detail_ib3);
		acti_news_detail_wv = (WebView) findViewById(R.id.acti_news_detail_wv);
		String url = getIntent().getStringExtra("url");
		
		WebSettings settings = acti_news_detail_wv.getSettings();
		settings.setJavaScriptEnabled(true);// ��ʾ֧��js
		settings.setBuiltInZoomControls(true);// ��ʾ�Ŵ���С��ť
		settings.setUseWideViewPort(true);// ֧��˫������

		acti_news_detail_wv.setWebViewClient(new WebViewClient() {

			/**
			 * ��ҳ��ʼ����
			 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				System.out.println("��ҳ��ʼ����");
				//pbProgress.setVisibility(View.VISIBLE);
			}

			/**
			 * ��ҳ���ؽ���
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				System.out.println("��ҳ��ʼ����");

				//pbProgress.setVisibility(View.GONE);
			}

			/**
			 * ������ת�����Ӷ����ڴ˷����лص�
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// tel:110
				System.out.println("��תurl:" + url);
				view.loadUrl(url);

				return true;
				// return super.shouldOverrideUrlLoading(view, url);
			}
		});
		//���ܸ�ǿ������ÿͻ���
		acti_news_detail_wv.setWebChromeClient(new WebChromeClient() {

			/**
			 * ���ȷ����仯
			 */
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				System.out.println("���ؽ���:" + newProgress);
				super.onProgressChanged(view, newProgress);
			}

			/**
			 * ��ȡ��ҳ����
			 */
			@Override
			public void onReceivedTitle(WebView view, String title) {
				System.out.println("��ҳ����:" + title);
				super.onReceivedTitle(view, title);
			}
		});

		
		// mWebView.goBack()
		acti_news_detail_wv.loadUrl(url);
		
		acti_news_detail_ib1.setOnClickListener(this);
		acti_news_detail_ib2.setOnClickListener(this);
		acti_news_detail_ib3.setOnClickListener(this);
	}
	
	
	
	
	private int mCurrentChooseItem;// ��¼��ǰѡ�е�item, ���ȷ��ǰ
	private int mCurrentItem = 2;// ��¼��ǰѡ�е�item, ���ȷ����

	/**
	 * ��ʾѡ��Ի���
	 */
	private void showChooseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		String[] items = new String[] { "���������", "�������", "��������", "С������",
				"��С������" };
		builder.setTitle("��������");
		builder.setSingleChoiceItems(items, mCurrentItem,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("ѡ��:" + which);
						mCurrentChooseItem = which;
					}
				});

		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				WebSettings settings = acti_news_detail_wv.getSettings();
				switch (mCurrentChooseItem) {
				case 0:
					settings.setTextSize(TextSize.LARGEST);
					break;
				case 1:
					settings.setTextSize(TextSize.LARGER);
					break;
				case 2:
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					settings.setTextSize(TextSize.SMALLER);
					break;
				case 4:
					settings.setTextSize(TextSize.SMALLEST);
					break;

				default:
					break;
				}

				mCurrentItem = mCurrentChooseItem;
			}
		});

		builder.setNegativeButton("ȡ��", null);

		builder.show();
	}

	/**
	 * ���ñ���������ĵ���¼�
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.acti_news_detail_ib1:
			finish();
			break;
		case R.id.acti_news_detail_ib2:
			showChooseDialog();
			break;
		case R.id.acti_news_detail_ib3:
			break;
		
		
		}
		
	}
}
