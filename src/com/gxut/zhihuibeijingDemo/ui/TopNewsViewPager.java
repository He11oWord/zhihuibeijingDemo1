package com.gxut.zhihuibeijingDemo.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ͷ�����ŵ�ViewPager
 * �н�����¼����ݵķ���
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
	 * �¼��ַ������󸸿ؼ������ڿؼ��Ƿ������¼�
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);//��getParent����
		return super.dispatchTouchEvent(ev);
	}
	
}
