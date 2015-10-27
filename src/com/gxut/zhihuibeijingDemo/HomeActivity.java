package com.gxut.zhihuibeijingDemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gxut.zhihuibeijingDemo.fragment.ActivityFragment;
import com.gxut.zhihuibeijingDemo.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setSliding();
		
		initFragment();
	}

	private void setSliding() {
		setBehindContentView(R.layout.sliding_menu);// 设置侧边栏布局
		SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
		//slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置展现模式
		slidingMenu.setBehindOffset(200);// 设置预留屏幕的宽度		
	}
	
	/**
	 * 初始化Fragment,然后把fragment的数据填充给布局文件
	 */
	private void initFragment(){
		//支持fragment的管家，这是v4包下面的，直接get可能在2.3崩掉
		FragmentManager fm = getSupportFragmentManager();
		//开启事务
		FragmentTransaction transaction = fm.beginTransaction();
	
		transaction.replace(R.id.activity_home_frame,new ActivityFragment());
		transaction.replace(R.id.sliding_frame,new LeftMenuFragment());
		
		transaction.commit();
	}
}
