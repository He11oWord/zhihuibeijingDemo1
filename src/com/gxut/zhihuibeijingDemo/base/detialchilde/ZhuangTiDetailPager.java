package com.gxut.zhihuibeijingDemo.base.detialchilde;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * 新闻-专题的内容，可动态设置
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:26
 */
public class ZhuangTiDetailPager extends BaseDetailPager {

	public ZhuangTiDetailPager(Activity activity) {
		super(activity);
	}

	public View initView() {
		TextView text = new TextView(mActivity);
		text.setText("新闻--专题");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
