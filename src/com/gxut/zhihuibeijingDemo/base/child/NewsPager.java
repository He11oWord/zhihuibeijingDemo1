package com.gxut.zhihuibeijingDemo.base.child;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxut.zhihuibeijingDemo.MainActivity;
import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;
import com.gxut.zhihuibeijingDemo.base.detialchilde.HudongDetailPager;
import com.gxut.zhihuibeijingDemo.base.detialchilde.XinwenDetailPager;
import com.gxut.zhihuibeijingDemo.base.detialchilde.ZhuangTiDetailPager;
import com.gxut.zhihuibeijingDemo.base.detialchilde.ZutuDetailPager;
import com.gxut.zhihuibeijingDemo.domin.NewsData;
import com.gxut.zhihuibeijingDemo.domin.NewsData.NewsMenuData;
import com.gxut.zhihuibeijingDemo.global.GlobalUrl;
import com.gxut.zhihuibeijingDemo.utils.CacheUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 新闻页面的布局，可动态设置
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:56
 */
public class NewsPager extends BasePager {

	private ArrayList<BaseDetailPager> bdList;
	private NewsData newsData;
	private MainActivity mainUI;

	public NewsPager(Activity activity) {
		super(activity);
		mainUI = (MainActivity) mActivity;
	}

	@Override
	public void initData() {
		tv_title.setText("新闻");
		setSlidingMenu(true);
		String cache = CacheUtil.getCache(mActivity, GlobalUrl.CATEGORIES_URL);
		if(!TextUtils.isEmpty(cache)){//如果不为空，先解析缓存
			parseData(cache);
		}
		getDataFromServer();//不管有没有缓存，都获取最新数据
		setDetailPager(mainUI.getFragmentLeftMenu().getMPostion());

		
		
		// 点击上方的小横线，出现菜单
		ib_menu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleSlidingMenu();
			}
		});

	}

	/**
	 * 切换侧边栏的状态
	 */
	protected void toggleSlidingMenu() {
		MainActivity mainUI = (MainActivity) mActivity;// 获得MainActivity
		mainUI.getSlidingMenu().toggle();
	}

	/**
	 * 从服务器获得数据
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();

		utils.send(HttpMethod.GET, GlobalUrl.CATEGORIES_URL,
				new RequestCallBack<String>() {

					// 访问成功
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String result = responseInfo.result;
						CacheUtil.setCache(mActivity, GlobalUrl.CATEGORIES_URL, result);
						
						parseData(result);
					}

					// 访问失败
					@Override
					public void onFailure(HttpException error, String msg) {
						Log.d("NewsPager", "失败" + msg);
						tv_title.setText("新闻");
						
					}	
				});
	}

	/**
	 * 获取json数据
	 * @param result
	 */
	protected void parseData(String result) {
		Gson gson = new Gson();
		newsData = gson.fromJson(result, NewsData.class);

		// 传递json资料
		
		mainUI.getFragmentLeftMenu().setNewsData(newsData);

		// 数组里面添加详情页面
		bdList = new ArrayList<BaseDetailPager>();
		bdList.add(new XinwenDetailPager(mActivity,newsData.data.get(0).children));
		bdList.add(new ZhuangTiDetailPager(mActivity));
		bdList.add(new ZutuDetailPager(mActivity,ib_serach));
		bdList.add(new HudongDetailPager(mActivity));

		// 预设值为首页
	
		
		
	}

	/**
	 * 设置显示哪个详情页面
	 * 
	 * @param position
	 */
	public void setDetailPager(int position) {
		bdList.get(position).initData();
		fl.removeAllViews();
		fl.addView(bdList.get(position).mView);

		//设置标题
		NewsMenuData newsMenuData = newsData.data.get(position);
		tv_title.setText(newsMenuData.title);
		if (bdList.get(position) instanceof ZutuDetailPager) {
			ib_serach.setVisibility(View.VISIBLE);
		} else {
			ib_serach.setVisibility(View.GONE);
		}
		
	}
}
