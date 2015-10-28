package com.gxut.zhihuibeijingDemo.base.child;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * ����ҳ��Ĳ��֣��ɶ�̬����
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:56
 */
public class NewsPager extends BasePager {

	public NewsPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tv_title.setText("����");
		setSlidingMenu(true);
		TextView text = new TextView(mActivity);
		text.setText("����");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		fl.addView(text);
		
	}

}
