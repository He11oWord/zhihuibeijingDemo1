package com.gxut.zhihuibeijingDemo.settingactiivty;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.Gson;
import com.gxut.zhihuibeijingDemo.NewsDetailActivity;
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseActivity;
import com.gxut.zhihuibeijingDemo.base.detialchilde.XinwenChilderDetailPager;
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

/**
 * 今日热点的界面
 */
public class TodayHotActivity extends BaseActivity {

	private RefreshListView top_list;
	private String mUrl;
	private NewsDetailData detailChilrenData;
	private String mLoadMoreUrl;
	private ArrayList<NewsDetailChilrenNewsData> newsList;
	private MyTopListAdapter myTopListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View initView() {
		super.initView();
		View view = View.inflate(this, R.layout.base_setting_todayhot, null);
		top_list = (RefreshListView) view
				.findViewById(R.id.base_setting_todayhot_lv);
		// 实现刷新的接口
		top_list.setOnRefreshListener(new onRefreshListener() {

			@Override
			public void onrefresh() {
				getDataFromServer();

			}

			@Override
			public void onLoadMore() {
				if (TextUtils.isEmpty(mLoadMoreUrl)) {
					Toast.makeText(TodayHotActivity.this, "到底了", 0).show();
					top_list.onRefreshComplete(false);
				} else {
					System.out.println("正在加载更多");
					getMoreDataFromServer();

				}
			}
		});
		// 实现单条点击事件,已封装的接口
		top_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String id = newsList.get(arg2).id;
				String ids = PrefUtils.getString(TodayHotActivity.this, "ids", "");
				if (ids.contains(id)) {
				} else {
					ids = ids + id + ",";
					PrefUtils.setString(TodayHotActivity.this, "ids", ids);
				}
				// myTopListAdapter.notifyDataSetChanged();这个方法整个屏幕都被刷了。
				changeTextColor(arg1);

				Intent intent = new Intent(TodayHotActivity.this, NewsDetailActivity.class);
				intent.putExtra("url", newsList.get(arg2).url);
				TodayHotActivity.this.startActivity(intent);
			}
		});
		return view;
	}

	@Override
	public void initData() {
		tv_title.setText("今日热点");

		String cache = CacheUtil.getCache(TodayHotActivity.this,
				GlobalUrl.SERVER_URL + "/10007/list_1.json");

		if (!TextUtils.isEmpty(cache)) {// 如果不为空，先解析缓存
			parseData(cache, true);
			Toast.makeText(this, "获得缓存成功", 0).show();
		}
		// 从服务器获得数据
		getDataFromServer();

	}

	/**
	 * 从服务器获得数据
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();

		mUrl = GlobalUrl.SERVER_URL + "/10007/list_1.json";
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			// 访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result, true);
				CacheUtil.setCache(TodayHotActivity.this, mUrl, result);
				top_list.onRefreshComplete(true);
			}

			// 访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Log.d("NewsPager", "失败" + msg);
				Toast.makeText(TodayHotActivity.this, "联网失败", 0).show();
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
				Toast.makeText(TodayHotActivity.this, "联网失败", 0).show();
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

			newsList = detailChilrenData.data.news;

			if (newsList != null) {
				myTopListAdapter = new MyTopListAdapter();
				top_list.setAdapter(myTopListAdapter);
			}

		} else {// 如果是加载更多的话，就添加列表
			List<NewsDetailChilrenNewsData> moreNewsList = detailChilrenData.data.news;
			newsList.addAll(moreNewsList);
			myTopListAdapter.notifyDataSetChanged();

		}

	}

	/**
	 * listView的适配器
	 */
	class MyTopListAdapter extends BaseAdapter {

		private BitmapUtils listBitmapUtils;

		public MyTopListAdapter() {
			listBitmapUtils = new BitmapUtils(TodayHotActivity.this);
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
				convertView = View.inflate(TodayHotActivity.this,
						R.layout.list_news_item, null);
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
			String ids = PrefUtils.getString(TodayHotActivity.this, "ids", "");
			if (ids.contains(getItem(position).id)) {
				holder.tv1.setTextColor(Color.GRAY);
			} else {
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

	/**
	 * 设置已经点击过条目的颜色
	 * 
	 * @param view
	 */
	public void changeTextColor(View view) {
		TextView tv_color = (TextView) view.findViewById(R.id.tv_title);
		tv_color.setTextColor(Color.GRAY);

	}

}
