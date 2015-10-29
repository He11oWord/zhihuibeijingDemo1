package com.gxut.zhihuibeijingDemo.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 头条新闻的ViewPager
 * 有禁点击事件传递的方法
 * @author lizhao
 *
 */
public class TopNewsViewPager extends ViewPager{

	public TopNewsViewPager(Context context) {
		super(context);
	}
	public TopNewsViewPager(Context context,AttributeSet a) {
		super(context,a);
	}
	
	
	/**
	 * 事件分发，请求父控件及祖宗控件是否拦截事件
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);//用getParent请求
		return super.dispatchTouchEvent(ev);
	}
	
}
