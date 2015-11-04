package com.gxut.zhihuibeijingDemo.base.child;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * ���˵����ݣ��ɶ�̬����
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:26
 */
public class ServicePager extends BasePager {

	public ServicePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tv_title.setText("����");

		ib_menu.setVisibility(View.GONE);
		setSlidingMenu(false);
		TextView text = new TextView(mActivity);
		text.setText("����");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		fl.addView(text);
	}

}
