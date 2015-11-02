package com.gxut.zhihuibeijingDemo.settingactiivty;

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
 * �������
 */
public class SuggestionActivity extends BaseActivity {
	private EditText et1;
	private EditText et2;
	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View initView() {
		View view = View.inflate(this, R.layout.base_activity_suggestion, null);
		et1 = (EditText) view.findViewById(R.id.et1);
		et2 = (EditText) view.findViewById(R.id.et2);
		button1 = (Button) view.findViewById(R.id.button);

		return view;
	}

	@Override
	public void initData() {
		tv_title.setText("�������");
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = et1.getText().toString();
				String password = et2.getText().toString();
				if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
					Toast.makeText(SuggestionActivity.this, "����������ϵ��ʽ����Ϊ��", 0).show();
				} else {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Toast.makeText(SuggestionActivity.this, "����ʧ��", 0).show();
					
				}
			}
		});
		

	}

}
