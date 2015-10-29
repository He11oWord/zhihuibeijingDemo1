package com.gxut.zhihuibeijingDemo.ui;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * @Description 不能滑动的ViewPager
 * @author lizhao
 * @date 2015-10-27 下午10:35:56
 */
public class NoScViewPager extends ViewPager {

	public NoScViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置不能滚动
	 */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}
	/**
	 * 不要阻止其他控件滚动
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
}
