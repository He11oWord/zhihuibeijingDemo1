package com.gxut.zhihuibeijingDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 今日热点的界面
 */
public class TodayHotActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_todathot);
		
	}
}
