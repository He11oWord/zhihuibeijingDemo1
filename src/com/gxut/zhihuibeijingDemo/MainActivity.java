package com.gxut.zhihuibeijingDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;

import com.gxut.zhihuibeijingDemo.domin.NewsData;
import com.gxut.zhihuibeijingDemo.fragment.ActivityFragment;
import com.gxut.zhihuibeijingDemo.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_ACTIVITY = "fragment_activity";

	private SlidingMenu slidingMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		setSliding();

		initFragment();
	}

	/**
	 * 设置侧边栏的布局
	 */
	private void setSliding() {
		setBehindContentView(R.layout.sliding_menu);// 设置侧边栏布局
		slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
		// slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置展现模式
		slidingMenu.setBehindOffset(120);// 设置预留屏幕的宽度
	}

	/**
	 * 初始化Fragment,然后把fragment的数据填充给布局文件
	 */
	private void initFragment() {
		// 支持fragment的管家，这是v4包下面的，直接get可能在2.3崩掉
		FragmentManager fm = getSupportFragmentManager();
		// 开启事务
		FragmentTransaction transaction = fm.beginTransaction();

		// 替换，并打上标记
		transaction.replace(R.id.activity_home_frame, new ActivityFragment(),
				FRAGMENT_ACTIVITY);
		transaction.replace(R.id.sliding_frame, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);

		transaction.commit();
	}

	/**
	 * 获得侧边栏
	 * @return 侧边栏
	 */
	public LeftMenuFragment getFragmentLeftMenu() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;

	}

	/**
	 * 获得主页面
	 * @return 侧边栏
	 */
	public ActivityFragment getActivityFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ActivityFragment findFragmentByTag = (ActivityFragment) fm.findFragmentByTag(FRAGMENT_ACTIVITY);
		return findFragmentByTag;

	}

}
