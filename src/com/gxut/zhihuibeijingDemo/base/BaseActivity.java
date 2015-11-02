package com.gxut.zhihuibeijingDemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.R;

public class BaseActivity extends Activity {

	public View mView;
	public ImageButton ib;
	public TextView tv_title;
	public LinearLayout ll;
	public View baseView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_base);
		ib = (ImageButton) findViewById(R.id.base_activity_back);
		tv_title = (TextView) findViewById(R.id.base_activity_tv_title);
		ll = (LinearLayout) findViewById(R.id.base_activity_ll);

		ib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		initView();
		addView();
		initData();
	}

	/**
	 * 初始化界面
	 */
	public View initView() {
		return null;
	}

	public void addView() {
		mView = initView();
		if (mView != null) {
			ll.addView(mView);
		}
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
	}

}
