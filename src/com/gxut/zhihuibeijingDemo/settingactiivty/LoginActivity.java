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
import com.gxut.zhihuibeijingDemo.base.child.PersonPager;
import com.gxut.zhihuibeijingDemo.utils.PrefUtils;

/**
 * ��½����
 */
public class LoginActivity extends BaseActivity {

	private EditText et1;
	private EditText et2;
	private Button button1;
	private Button button2;
	private PersonPager pp;

	// public LoginActivity(PersonPager pp){
	// this.pp = pp;
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onRestart() {
		initData();
		super.onRestart();
	}

	@Override
	protected void onResume() {
		initData();
		super.onResume();
	}

	@Override
	public View initView() {
		View view = View.inflate(this, R.layout.base_activity_login, null);
		et1 = (EditText) view.findViewById(R.id.et1);
		et2 = (EditText) view.findViewById(R.id.et2);
		button1 = (Button) view.findViewById(R.id.button1);
		button2 = (Button) view.findViewById(R.id.button2);
		return view;
	}

	@Override
	public void initData() {
		tv_title.setText("��½");
		// ����2����ť�ĵ���¼�
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String number = et1.getText().toString();
				String password = et2.getText().toString();
				if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
					Toast.makeText(LoginActivity.this, "�û����������벻��Ϊ��", 0).show();
				} else {
					if (password.equals("123")) {
						Toast.makeText(LoginActivity.this, "��½�ɹ�", 0).show();
						PrefUtils.setBoolean(getApplicationContext(),
								"isLogin", true);
						finish();
						// pp.initData();

					} else {
						Toast.makeText(LoginActivity.this, "�������", 0).show();
						PrefUtils.setBoolean(getApplicationContext(),
								"isLogin", false);
					}
				}
			}
		});

		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(LoginActivity.this,
						ZhuceActivity.class);
				startActivity(intent);
			}
		});
	}

}
