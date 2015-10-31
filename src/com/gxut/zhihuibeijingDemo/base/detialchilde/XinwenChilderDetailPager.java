package com.gxut.zhihuibeijingDemo.base.detialchilde;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxut.zhihuibeijingDemo.NewsDetailActivity;
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.domin.NewsData.NewsTabData;
import com.gxut.zhihuibeijingDemo.domin.NewsDetailData;
import com.gxut.zhihuibeijingDemo.domin.NewsDetailData.NewsDetailChilrenNewsData;
import com.gxut.zhihuibeijingDemo.global.GlobalUrl;
import com.gxut.zhihuibeijingDemo.ui.RefreshListView;
import com.gxut.zhihuibeijingDemo.ui.RefreshListView.onRefreshListener;
import com.gxut.zhihuibeijingDemo.utils.CacheUtil;
import com.gxut.zhihuibeijingDemo.utils.PrefUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

public class XinwenChilderDetailPager extends BaseDetailPager implements
		OnPageChangeListener {

	private NewsTabData newsTabData;// 传进来的结果
	private TextView tv_title;// 头条标题
	private NewsDetailData detailChilrenData;// 解析出来的结果
	private ViewPager detailVp;// 设置头条新闻所有图片
	private BitmapUtils bitmapUtils;// 设置头条新闻的图片
	private CirclePageIndicator mIndicator;// 设置头条新闻的小圆点
	private RefreshListView top_list;//新闻列表
	private MyTopListAdapter myTopListAdapter;//新闻列表的适配器
	private List<NewsDetailChilrenNewsData> newsList;
	private String mLoadMoreUrl;//more的地址
	private String mUrl;

	public XinwenChilderDetailPager(Activity activity, NewsTabData newsTabData1) {
		super(activity);
		mView = initView();
		newsTabData = newsTabData1;
	}

	/**
	 * 初始化布局
	 */
	@Override
	public View initView() {
		
		View view = View.inflate(mActivity, R.layout.news_detail, null);
		View headView = View.inflate(mActivity, R.layout.top_headview, null);
		detailVp = (ViewPager) headView.findViewById(R.id.news_detail_vp);
		tv_title = (TextView) headView.findViewById(R.id.tv_title);
		mIndicator = (CirclePageIndicator) headView
				.findViewById(R.id.indicator);
		top_list = (RefreshListView) view.findViewById(R.id.news_detail_lv);
		top_list.addHeaderView(headView);

		//实现刷新的接口
		top_list.setOnRefreshListener(new onRefreshListener() {

			@Override
			public void onrefresh() {
				getDataFromServer();

			}

			@Override
			public void onLoadMore() {
				if (TextUtils.isEmpty(mLoadMoreUrl)) {
					Toast.makeText(mActivity, "到底了", 0).show();
					top_list.onRefreshComplete(false);
				} else {
					System.out.println("正在加载更多");
					getMoreDataFromServer();
					
				}
			}
		});
		//实现单条点击事件,已封装的接口
		top_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String id = newsList.get(arg2).id;
				String ids = PrefUtils.getString(mActivity,"ids", "");
				if(ids.contains(id)){
				}else{
					ids =ids + id +",";
					PrefUtils.setString(mActivity, "ids", ids);
				}
				//myTopListAdapter.notifyDataSetChanged();这个方法整个屏幕都被刷了。
				changeTextColor(arg1);
				
				Intent intent = new Intent(mActivity,NewsDetailActivity.class);
				intent.putExtra("url", newsList.get(arg2).url);
				mActivity.startActivity(intent);
			}
		});
		return view;
	}

	public void changeTextColor(View view){
		TextView tv_color = (TextView) view.findViewById(R.id.tv_title);
		tv_color.setTextColor(Color.GRAY);
	
	}
	
	@Override
	public void initData() {
		String cache = CacheUtil.getCache(mActivity, GlobalUrl.SERVER_URL + newsTabData.url);
		
		if(!TextUtils.isEmpty(cache)){//如果不为空，先解析缓存
			parseData(cache,true);
			System.out.println("获得缓存成功");
			Toast.makeText(mActivity, "获得缓存成功", 0).show();
		}
		
		// 从服务器获得数据
		getDataFromServer();

	}

	/**
	 * 从服务器获得数据
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		System.out.println(GlobalUrl.CATEGORIES_URL + newsTabData.url);

		mUrl = GlobalUrl.SERVER_URL + newsTabData.url;
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			// 访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result, true);
				CacheUtil.setCache(mActivity, mUrl, result);
				top_list.onRefreshComplete(true);
			}

			// 访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Log.d("NewsPager", "失败" + msg);
				Toast.makeText(mActivity, "联网失败", 0).show();
				top_list.onRefreshComplete(false);
			}
		});
	}

	/**
	 * 加载更多的数据
	 */
	private void getMoreDataFromServer() {
		HttpUtils utils = new HttpUtils();

		utils.send(HttpMethod.GET, mLoadMoreUrl, new RequestCallBack<String>() {

			// 访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result, false);

				top_list.onRefreshComplete(true);
			}

			// 访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Log.d("NewsPager", "失败" + msg);
				Toast.makeText(mActivity, "联网失败", 0).show();
				top_list.onRefreshComplete(false);
			}
		});
	}

	/**
	 * 获取json数据
	 * 
	 * @param result
	 */
	protected void parseData(String result, boolean isMore) {
		Gson gson = new Gson();
		NewsDetailData fromJson = gson.fromJson(result, NewsDetailData.class);
		detailChilrenData = fromJson;
		if (TextUtils.isEmpty(fromJson.data.more)) {
			mLoadMoreUrl = null;
		} else {
			mLoadMoreUrl = GlobalUrl.SERVER_URL + fromJson.data.more;
			
		}

		if (isMore) {
			if (detailChilrenData.data.topnews != null) {
				detailVp.setAdapter(new MyChildPagerAdapter());
				mIndicator.setViewPager(detailVp);
				mIndicator.setSnap(true);// 快照显示
				mIndicator.setOnPageChangeListener(this);
				mIndicator.onPageSelected(0);
				tv_title.setText(detailChilrenData.data.topnews.get(0).title);
			}

			newsList = detailChilrenData.data.news;

			if (newsList != null) {
				myTopListAdapter = new MyTopListAdapter();
				top_list.setAdapter(myTopListAdapter);
			}
		} else {
			List<NewsDetailChilrenNewsData> moreNewsList = detailChilrenData.data.news;
			newsList.addAll(moreNewsList);
			myTopListAdapter.notifyDataSetChanged();

		}

	}

	/**
	 * 头条viewPager适配器
	 * 
	 * @author lizhao
	 * 
	 */
	class MyChildPagerAdapter extends PagerAdapter {

		public MyChildPagerAdapter() {
			bitmapUtils = new BitmapUtils(mActivity);
			bitmapUtils
					.configDefaultLoadingImage(R.drawable.home_scroll_default);

		}

		@Override
		public int getCount() {
			return detailChilrenData.data.topnews.size();
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
			container.addView(iv);
			bitmapUtils.display(iv,
					detailChilrenData.data.topnews.get(position).topimage);
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	/**
	 * listView的适配器
	 */
	class MyTopListAdapter extends BaseAdapter {

		private BitmapUtils listBitmapUtils;

		public MyTopListAdapter() {
			listBitmapUtils = new BitmapUtils(mActivity);
			listBitmapUtils
					.configDefaultLoadingImage(R.drawable.home_scroll_default);
		}

		@Override
		public int getCount() {
			return newsList.size();
		}

		@Override
		public NewsDetailChilrenNewsData getItem(int position) {
			return newsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.list_news_item,
						null);
				holder = new ViewHolder();
				holder.tv1 = (TextView) convertView.findViewById(R.id.tv_title);
				holder.tv2 = (TextView) convertView.findViewById(R.id.tv_date);
				holder.iv = (ImageView) convertView.findViewById(R.id.iv_pic);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NewsDetailChilrenNewsData newsDetailChilrenNewsData = newsList
					.get(position);
			holder.tv1.setText(newsDetailChilrenNewsData.title);
			holder.tv2.setText(newsDetailChilrenNewsData.pubdate);
			listBitmapUtils.display(holder.iv,
					newsDetailChilrenNewsData.listimage);
		
			
			getItem(position);
			String ids = PrefUtils.getString(mActivity,"ids", "");
			if(ids.contains(getItem(position).id)){
				holder.tv1.setTextColor(Color.GRAY);
			}else{
				holder.tv1.setTextColor(Color.BLACK);
			}
			
			return convertView;
		}

	}

	/**
	 * view管理类
	 */
	class ViewHolder {
		public TextView tv1;
		public TextView tv2;
		public ImageView iv;
	}

	@Override
	public void onPageSelected(int arg0) {
		tv_title.setText(detailChilrenData.data.topnews.get(arg0).title);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
}
