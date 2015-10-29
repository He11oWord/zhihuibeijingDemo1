package com.gxut.zhihuibeijingDemo.base.child;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * 个人页面的布局，可动态设置
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:56
 */
public class PesonPager extends BasePager {

	public PesonPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		tv_title.setText("个人");
		ib_menu.setVisibility(View.GONE);
		setSlidingMenu(false);
		TextView text = new TextView(mActivity);
		text.setText("个人");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		fl.addView(text);
		
	}

}
