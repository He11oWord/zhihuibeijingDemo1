package com.gxut.zhihuibeijingDemo.settingactiivty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseActivity;

/**
 * 注册界面
 */
public class ZhuceActivity extends BaseActivity {

	private EditText et1;
	private EditText et2;
	private EditText et3;
	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View initView() {
		View view = View.inflate(this, R.layout.base_activity_zhuce, null);
		et1 = (EditText) view.findViewById(R.id.et1);
		et2 = (EditText) view.findViewById(R.id.et2);
		et3 = (EditText) view.findViewById(R.id.et3);
		button1 = (Button) view.findViewById(R.id.button1);
		button2 = (Button) view.findViewById(R.id.button2);
		return view;
	}

	@Override
	public void initData() {
		tv_title.setText("注册");
		// 设置2个按钮的点击事件
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String number = et1.getText().toString();
				String password = et2.getText().toString();
				String passwordSecond = et3.getText().toString();
				if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)
						|| TextUtils.isEmpty(passwordSecond)) {
					Toast.makeText(ZhuceActivity.this, "用户名或者密码不能为空", 0).show();
				} else {
					if (password.equals(passwordSecond)) {
						finish();
						Toast.makeText(ZhuceActivity.this, "注册成功", 0).show();
						Intent intent = new Intent(ZhuceActivity.this,
								LoginActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(ZhuceActivity.this, "2次密码不一样", 0).show();
					}
				}
			}
		});

		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(ZhuceActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		});
	}
}
