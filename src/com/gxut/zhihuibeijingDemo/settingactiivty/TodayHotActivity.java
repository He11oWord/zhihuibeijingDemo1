package com.gxut.zhihuibeijingDemo.settingactiivty;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 今日热点的界面
 */
public class TodayHotActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	}

	@Override
	public View initView() {
		super.initView();
		View view = View.inflate(this, R.layout.base_setting_todayhot, null);
		ListView lv = (ListView) view.findViewById(R.id.base_setting_todayhot_lv);
		return view;
	}
	
	@Override
	public void initData() {
		tv_title.setText("今日热点");
	
	}
}
