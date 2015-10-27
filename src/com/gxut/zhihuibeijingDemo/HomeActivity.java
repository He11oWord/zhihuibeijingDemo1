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
		setBehindContentView(R.layout.sliding_menu);// ���ò��������
		SlidingMenu slidingMenu = getSlidingMenu();// ��ȡ���������
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// ����ȫ������
		//slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// ����չ��ģʽ
		slidingMenu.setBehindOffset(200);// ����Ԥ����Ļ�Ŀ��		
	}
	
	/**
	 * ��ʼ��Fragment,Ȼ���fragment���������������ļ�
	 */
	private void initFragment(){
		//֧��fragment�Ĺܼң�����v4������ģ�ֱ��get������2.3����
		FragmentManager fm = getSupportFragmentManager();
		//��������
		FragmentTransaction transaction = fm.beginTransaction();
	
		transaction.replace(R.id.activity_home_frame,new ActivityFragment());
		transaction.replace(R.id.sliding_frame,new LeftMenuFragment());
		
		transaction.commit();
	}
}
