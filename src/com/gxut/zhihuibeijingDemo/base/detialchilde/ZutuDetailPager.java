package com.gxut.zhihuibeijingDemo.base.detialchilde;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * ����-��ͼ�����ݣ��ɶ�̬����
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:26
 */
public class ZutuDetailPager extends BaseDetailPager {

	public ZutuDetailPager(Activity activity) {
		super(activity);
	}

//	@Override
//	public void initData() {
//		//tv_title.setText("����");
//		//setSlidingMenu(true);
//		TextView text = new TextView(mActivity);
//		text.setText("����--��ҳ");
//		text.setTextColor(Color.RED);
//		text.setTextSize(25);
//		text.setGravity(Gravity.CENTER);
//		fl.addView(text);
//		
//	}
	@Override
	public View initView() {
		TextView text = new TextView(mActivity);
		text.setText("����--��ͼ");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
