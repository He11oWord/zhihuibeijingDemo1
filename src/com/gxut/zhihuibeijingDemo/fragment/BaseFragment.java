package com.gxut.zhihuibeijingDemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment的基类
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 上午12:16:08
 */
public abstract class BaseFragment extends Fragment {

	public Activity mActivity;

	//在活动创建完成是调用
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	//fragment创建的时候调用
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	//绘制里面的布局，相对来说重要
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initViews();
	}

	/**
	 * 初始化界面
	 */
	public abstract View initViews();
	
	/**
	 * 初始化数据
	 */
	public void initData(){
		
	}
}
