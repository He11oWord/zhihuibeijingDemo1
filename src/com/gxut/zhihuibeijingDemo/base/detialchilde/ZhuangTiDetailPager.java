package com.gxut.zhihuibeijingDemo.base.detialchilde;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;

/**
 * ����-ר������ݣ��ɶ�̬����
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:26
 */
public class ZhuangTiDetailPager extends BaseDetailPager {

	public ZhuangTiDetailPager(Activity activity) {
		super(activity);
	}

	public View initView() {
		TextView text = new TextView(mActivity);
		text.setText("����--ר��");
		text.setTextColor(Color.RED);
		text.setTextSize(25);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
