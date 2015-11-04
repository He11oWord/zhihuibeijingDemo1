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
	 * ���ò�����Ĳ���
	 */
	private void setSliding() {
		setBehindContentView(R.layout.sliding_menu);// ���ò��������
		slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// ����ȫ������
		// slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// ����չ��ģʽ
		slidingMenu.setBehindOffset(120);// ����Ԥ����Ļ�Ŀ��
	}

	/**
	 * ��ʼ��Fragment,Ȼ���fragment���������������ļ�
	 */
	private void initFragment() {
		// ֧��fragment�Ĺܼң�����v4������ģ�ֱ��get������2.3����
		FragmentManager fm = getSupportFragmentManager();
		// ��������
		FragmentTransaction transaction = fm.beginTransaction();

		// �滻�������ϱ��
		transaction.replace(R.id.activity_home_frame, new ActivityFragment(),
				FRAGMENT_ACTIVITY);
		transaction.replace(R.id.sliding_frame, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);

		transaction.commit();
	}

	/**
	 * ��ò����
	 * @return �����
	 */
	public LeftMenuFragment getFragmentLeftMenu() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;

	}

	/**
	 * �����ҳ��
	 * @return �����
	 */
	public ActivityFragment getActivityFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ActivityFragment findFragmentByTag = (ActivityFragment) fm.findFragmentByTag(FRAGMENT_ACTIVITY);
		return findFragmentByTag;

	}

}
