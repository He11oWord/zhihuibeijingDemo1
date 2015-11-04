package com.gxut.zhihuibeijingDemo.base.detialchilde;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;
import com.gxut.zhihuibeijingDemo.base.detialchilde.ZhuangTiDetailPager.MyListAdapter;
import com.gxut.zhihuibeijingDemo.base.detialchilde.ZhuangTiDetailPager.ViewHolder;
import com.gxut.zhihuibeijingDemo.domin.ZutuData;
import com.gxut.zhihuibeijingDemo.domin.ZutuData.ZutuNewsInfo;
import com.gxut.zhihuibeijingDemo.global.GlobalUrl;
import com.gxut.zhihuibeijingDemo.utils.CacheUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 新闻-互动的内容，可动态设置
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:26
 */
public class HudongDetailPager extends BaseDetailPager {

	private View view;
	private ZutuData zutuData;
	private String mLoadMoreUrl;
	private ListView lv_zutu;
	private GridView gv_zutu;
	private MyListAdapter adapter;
	private ArrayList<ZutuNewsInfo> zutuList;
	private ImageButton ib;

	public HudongDetailPager(Activity activity) {
		super(activity);
	}
	boolean isListView = true;
	/**
	 * 改变视图
	 */
	protected void changeView() {
		if (isListView) {
			isListView = false;
			ib.setBackgroundResource(R.drawable.icon_pic_list_type);
			lv_zutu.setVisibility(View.GONE);
			gv_zutu.setVisibility(View.VISIBLE);
		}else{
			isListView = true;
			ib.setBackgroundResource(R.drawable.icon_pic_grid_type);
			gv_zutu.setVisibility(View.GONE);
			lv_zutu.setVisibility(View.VISIBLE);
		}
	}
	
	
	public View initView() {
		view = View.inflate(mActivity, R.layout.zutu_pager, null);
		lv_zutu = (ListView) view.findViewById(R.id.zutu_lv);
		gv_zutu = (GridView) view.findViewById(R.id.zutu_gv);
		return view;
	}

	@Override
	public void initData() {
		String cache = CacheUtil.getCache(mActivity, GlobalUrl.ZUTU_URL);

		if (!TextUtils.isEmpty(cache)) {
			parseData(cache);
		}

		getServerData();
	}
	
	/**
	 * 获得网络数据
	 */
	private void getServerData() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalUrl.ZUTU_URL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						System.out.println("解析成功");
						CacheUtil.setCache(mActivity, GlobalUrl.ZUTU_URL,
								result);
						parseData(result);

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("解析失败");
						System.out.println(GlobalUrl.ZUTU_URL);
						Log.d("ZuTUDp", msg);

					}
				});
	}

	/**
	 * 解析json数据
	 * 
	 * @param cache
	 */
	private void parseData(String result) {
		Gson gson = new Gson();
		zutuData = gson.fromJson(result, ZutuData.class);
		if (TextUtils.isEmpty(zutuData.data.more)) {
			mLoadMoreUrl = null;
		} else {
			mLoadMoreUrl = GlobalUrl.SERVER_URL + zutuData.data.more;
		}
		zutuList = zutuData.data.news;

		if (zutuList != null) {
			adapter = new MyListAdapter();
			lv_zutu.setAdapter(adapter);
			gv_zutu.setAdapter(adapter);
		}
	}

	/**
	 * 适配器
	 * 
	 * @author lizhao
	 * 
	 */
	class MyListAdapter extends BaseAdapter {

		private BitmapUtils listBitmapUtils;

		public MyListAdapter() {
			listBitmapUtils = new BitmapUtils(mActivity);
			listBitmapUtils
					.configDefaultLoadingImage(R.drawable.home_scroll_default);
		}

		@Override
		public int getCount() {
			return zutuList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = new ViewHolder();
			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.zutu_list_item,
						null);
				viewHolder.iv = (ImageView) convertView
						.findViewById(R.id.zutu_list_item_iv);
				viewHolder.tv = (TextView) convertView
						.findViewById(R.id.zutu_list_item_tv);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			listBitmapUtils.display(viewHolder.iv,
					zutuList.get(position).listimage);
			viewHolder.tv.setText(zutuList.get(position).title);
			return convertView;
		}

	}

	/**
	 * 布局管理
	 */
	class ViewHolder {
		private ImageView iv;
		private TextView tv;

	}
}
