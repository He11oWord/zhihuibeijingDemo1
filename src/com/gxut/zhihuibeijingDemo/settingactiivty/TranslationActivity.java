package com.gxut.zhihuibeijingDemo.settingactiivty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseActivity;

public class TranslationActivity extends BaseActivity {

	private EditText et;
	private Button button;
	private TextView tv;
	private static String path = "data/data/com.gxut.zhihuibeijingDemo/files/dictionary.db";
	private String s;
	private SQLiteDatabase openDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View initView() {
		View view = View
				.inflate(this, R.layout.base_activity_translation, null);
		et = (EditText) view.findViewById(R.id.et);
		button = (Button) view.findViewById(R.id.button);
		tv = (TextView) view.findViewById(R.id.tv);
		
		

		openDatabase = SQLiteDatabase.openDatabase(
				path, null, SQLiteDatabase.OPEN_READONLY);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				s = et.getText().toString();
				if (!TextUtils.isEmpty(s)) {
					
					Cursor cursor = openDatabase.rawQuery(
							"select chinese from t_words where english = ?",
							new String[] {s});
					if (cursor.moveToNext()) {
						String s1 = cursor.getString(0);
						tv.setText(s1);
					}else{
						tv.setText("太难了，臣妾做不到啊");
					}
					cursor.close();
				}else{
					Toast.makeText(TranslationActivity.this, "查询内容不为空", 0).show();
				}
			}
		});
		return view;
	}

	@Override
	public void initData() {
	

	}

	

}
