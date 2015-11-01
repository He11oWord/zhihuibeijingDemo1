package com.gxut.zhihuibeijingDemo.base.child;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gxut.zhihuibeijingDemo.NewsDetailActivity;
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BasePager;
import com.gxut.zhihuibeijingDemo.domin.HomeNewsData;
import com.gxut.zhihuibeijingDemo.domin.HomeNewsData.HomeTopData;
import com.gxut.zhihuibeijingDemo.global.GlobalUrl;
import com.gxut.zhihuibeijingDemo.utils.CacheUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * 主页的内容，可动态设置
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:26
 */
public class HomePager extends BasePager implements OnItemClickListener  {

	private CirclePageIndicator indicator;
	private TextView home_tv;
	private ViewPager home_vp;
	private GridView home_gv;
	private View homeView;
	private HomeNewsData topData;
	private ArrayList<HomeTopData> topNewsList;
	private Handler mHandler;
	private MyListAdapter myGridAdapter;
	private int[] ids = {
		R.drawable.today_hot,R.drawable.fm_shouye,R.drawable.fm_shouye,
		R.drawable.tubiao_pindao,R.drawable.index_chuxing,R.drawable.index_itlx,
		R.drawable.zhuanti_top,R.drawable.jiaoyu,R.drawable.yiliao
	};
	
	public HomePager(Activity activity) {
		super(activity);
		initData();
	}

	@Override
	public void initView() {
		super.initView();
		homeView = View.inflate(mActivity, R.layout.pager_home, null);
		home_gv = (GridView) homeView.findViewById(R.id.home_top_gv);
		home_vp = (ViewPager) homeView.findViewById(R.id.home_top_vp);
		home_tv = (TextView) homeView.findViewById(R.id.home_top_title);
		indicator = (CirclePageIndicator) homeView
				.findViewById(R.id.home_top_indicator);
		mView = homeView;
	}

	@Override
	public void initData() {

		setSlidingMenu(false);
		home_gv.setAdapter(myGridAdapter);
		home_gv.setOnItemClickListener(this);
		String cache = CacheUtil.getCache(mActivity, GlobalUrl.HOME_TOP_URL);
		if (!TextUtils.isEmpty(cache)) {// 如果不为空，先解析缓存
			parseData(cache);
			System.out.println("获得缓存成功");
			Toast.makeText(mActivity, "获得缓存成功", 0).show();
		}
		// 从服务器获得数据
		getDataFromServer();

		home_vp.setOnTouchListener(new MyTouchListener());
		myGridAdapter = new MyListAdapter();

		
	}

	/**
	 * 从服务器获得数据
	 */
	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalUrl.HOME_TOP_URL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						System.out.println("联网成功" + responseInfo.result);
						CacheUtil.setCache(mActivity, GlobalUrl.HOME_TOP_URL,
								result);
						parseData(result);

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("联网失败" + msg);

					}
				});

	}

	/**
	 * 解析获得的json
	 * 
	 * @param cache
	 */
	private void parseData(String result) {
		Gson gson = new Gson();
		topData = gson.fromJson(result, HomeNewsData.class);

		System.out.println("我被拿到了了");
		topNewsList = topData.data.topnews;

		home_vp.setAdapter(new MyTopPagerAdapter());
		indicator.setViewPager(home_vp);

	}

	/**
	 * 头条viewPager适配器
	 */
	class MyTopPagerAdapter extends PagerAdapter {

		private BitmapUtils bitmapUtils;

		public MyTopPagerAdapter() {
			bitmapUtils = new BitmapUtils(mActivity);
			bitmapUtils
					.configDefaultLoadingImage(R.drawable.home_scroll_default);

		}

		@Override
		public int getCount() {
			return topNewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = new ImageView(mActivity);
			iv.setImageResource(R.drawable.e);
			iv.setScaleType(ScaleType.FIT_XY);
			// iv.setImageResource(R.drawable.home_scroll_default);
			container.addView(iv);
			bitmapUtils.display(iv, topNewsList.get(position).topimage);
			home_tv.setText(topNewsList.get(position).title);

			// 自动轮播显示
			if (mHandler == null) {
				mHandler = new Handler() {
					public void handleMessage(android.os.Message msg) {

						// 获得正在显示的条目
						int currentItem = home_vp.getCurrentItem();

						if (currentItem < 3) {
							currentItem++;
						} else {
							currentItem = 0;
						}
						home_vp.setCurrentItem(currentItem);// 切换到下一个页面
						mHandler.sendEmptyMessageDelayed(0, 2000);// 以后循环发送
					};
				};
				mHandler.sendEmptyMessageDelayed(0, 2000);// 第一次2秒之后发信息出去
			}

			return iv;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	/**
	 * ViewPager的触摸事件
	 * 
	 * @author lizhao
	 */
	class MyTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mHandler.removeCallbacksAndMessages(null);// 清除所有的消息事件
				break;
			case MotionEvent.ACTION_CANCEL:
				mHandler.sendEmptyMessageDelayed(0, 3000);
				break;
			case MotionEvent.ACTION_UP:
				mHandler.sendEmptyMessageDelayed(0, 3000);
				break;
			}

			return false;
		}
	}
	
	private class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 9;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View viewItem = View.inflate(mActivity, R.layout.home_gv_item, null);
			ImageView gv_iv = (ImageView) viewItem.findViewById(R.id.home_gv_item_iv);
			gv_iv.setBackgroundResource(ids[arg0]);
			return viewItem;
		}
		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
	}

	/**
	 * 实现条目点击
	 * @param arg0
	 * @param arg1
	 * @param position
	 * @param arg3
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent;
		switch(position){
		case 1:
			break;
		case 7:
			System.out.println("求职招聘被点击了");
			intent = new Intent(mActivity,NewsDetailActivity.class);
			intent.putExtra("url", "http://www.baidu.com");
			mActivity.startActivity(intent);
			
			break;
		
		default:
			break;
		}
	}
}
