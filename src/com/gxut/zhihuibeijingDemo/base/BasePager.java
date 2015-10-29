package com.gxut.zhihuibeijingDemo.base;

import com.gxut.zhihuibeijingDemo.MainActivity;
import com.gxut.zhihuibeijingDemo.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 5个页面的共同基类
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午8:47:53
 */
public class BasePager {

	public Activity mActivity;
	public View mView;
	public TextView tv_title;
	public FrameLayout fl;
	public ImageButton ib_menu;
	public ImageButton ib_serach;

	public BasePager(Activity activity) {
		mActivity = activity;
		initView();
	}

	public void initView() {
		mView = View.inflate(mActivity, R.layout.base_pager, null);
		tv_title = (TextView) mView.findViewById(R.id.base_pager_tv_title);
		fl = (FrameLayout) mView.findViewById(R.id.base_pager_fl);
		ib_menu = (ImageButton) mView.findViewById(R.id.base_pager_ib1);
		ib_serach = (ImageButton) mView.findViewById(R.id.base_pager_ib2);
	}

	public void initData() {
	}

	/**
	 * 设置侧边菜单是否能滑动
	 * @param isOpen 是否打开
	 */
	public void setSlidingMenu(Boolean isOpen) {
		MainActivity ha = (MainActivity) mActivity;
		SlidingMenu slidingMenu = ha.getSlidingMenu();
		// slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//
		// 设置全屏触摸

		if (isOpen) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}
}
