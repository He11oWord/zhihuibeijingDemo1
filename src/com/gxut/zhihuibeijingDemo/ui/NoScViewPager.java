package com.gxut.zhihuibeijingDemo.ui;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * @Description ���ܻ�����ViewPager
 * @author lizhao
 * @date 2015-10-27 ����10:35:56
 */
public class NoScViewPager extends ViewPager {

	public NoScViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * ���ò��ܹ���
	 */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}
	/**
	 * ��Ҫ��ֹ�����ؼ�����
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
}
