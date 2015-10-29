package com.gxut.zhihuibeijingDemo.base.child;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * ��ҳ�����ݣ��ɶ�̬����
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:26
 */
public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tv_title.setText("�ǻ۱���");
		setSlidingMenu(false);
		ib_menu.setVisibility(View.INVISIBLE);
		TextView text = new TextView(mActivity);
		text.setText("��ҳ");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		fl.addView(text);
		
	}

}
