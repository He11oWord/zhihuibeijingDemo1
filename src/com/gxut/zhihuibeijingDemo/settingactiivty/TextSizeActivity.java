package com.gxut.zhihuibeijingDemo.settingactiivty;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseActivity;
import com.gxut.zhihuibeijingDemo.utils.PrefUtils;

public class TextSizeActivity extends BaseActivity {

	private TextView tv;
	private CheckBox cb5;
	private CheckBox cb4;
	private CheckBox cb3;
	private CheckBox cb2;
	private CheckBox cb1;
	private RadioGroup rg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View initView() {

		View view = View.inflate(this, R.layout.base_activity_textsize, null);
		tv = (TextView) view.findViewById(R.id.tv);
		rg = (RadioGroup) view.findViewById(R.id.rg);
		setCheck();
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				PrefUtils.setInt(getApplicationContext(), "textsizecheck", checkedId);
			}
		});
		return view;
	}

	@Override
	public void initData() {
		tv_title.setText("◊÷ÃÂ…Ë÷√");


	}

	private void setCheck(){
		int checkId = PrefUtils.getInt(this, "textsizecheck", 2);
		rg.check(checkId);
	}
	
	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}
	
}
