package com.gxut.zhihuibeijingDemo.base.detialchilde;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * 新闻-组图的内容，可动态设置
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:26
 */
public class ZutuDetailPager extends BaseDetailPager {

	public ZutuDetailPager(Activity activity) {
		super(activity);
	}

//	@Override
//	public void initData() {
//		//tv_title.setText("新闻");
//		//setSlidingMenu(true);
//		TextView text = new TextView(mActivity);
//		text.setText("新闻--首页");
//		text.setTextColor(Color.RED);
//		text.setTextSize(25);
//		text.setGravity(Gravity.CENTER);
//		fl.addView(text);
//		
//	}
	@Override
	public View initView() {
		TextView text = new TextView(mActivity);
		text.setText("新闻--组图");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
