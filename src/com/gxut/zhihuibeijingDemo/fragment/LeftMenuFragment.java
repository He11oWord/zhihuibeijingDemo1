package com.gxut.zhihuibeijingDemo.fragment;

import com.gxut.zhihuibeijingDemo.R;

import android.view.View;


/**
 * 侧边抽屉菜单的布局
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 上午12:26:56
 */
public class LeftMenuFragment extends BaseFragment{

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu	, null);
		return view;
	}

}
