package com.gxut.zhihuibeijingDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * �����ȵ�Ľ���
 */
public class TodayHotActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_todathot);
		
	}
}
