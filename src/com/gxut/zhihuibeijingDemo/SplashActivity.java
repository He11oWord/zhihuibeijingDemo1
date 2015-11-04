package com.gxut.zhihuibeijingDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.gxut.zhihuibeijingDemo.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * SplashActivityҳ��
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-26 ����2:35:06
 */
public class SplashActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	
		RelativeLayout relative = (RelativeLayout) findViewById(R.id.splash_relative);
		startAnimation(relative);
		copyDB();

	}

	/**
	 * ���Ŷ��� ��ת/����/����
	 * 
	 * @param relative
	 */
	private void startAnimation(RelativeLayout relative) {
		// װ����������
		AnimationSet set = new AnimationSet(false);

		// ��ת�Ķ���
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(1000);// ʱ��
		rotate.setFillAfter(true);// �Ƿ񱣳ֶ���

		// ���ŵĶ���
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
				0.3f);
		scale.setDuration(1000);// ʱ��
		scale.setFillAfter(true);// �Ƿ񱣳ֶ���

		// ����Ķ���
		AlphaAnimation alphe = new AlphaAnimation(0, 1);
		alphe.setDuration(1000);// ʱ��
		alphe.setFillAfter(true);// �Ƿ񱣳ֶ���

		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alphe);

		set.setAnimationListener(new AnimationListener() {

			// ������ʼ��ʱ��
			@Override
			public void onAnimationStart(Animation animation) {
			}

			// ���ŵ�ʱ��
			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			// ��������
			@Override
			public void onAnimationEnd(Animation animation) {
				// ��������ҳ�������ҳ��
				nextActivity();
			}

		});
		relative.startAnimation(set);
	}

	/**
	 * ����Shared ��������ҳ�������ҳ��
	 */
	private void nextActivity() {

//		if (PrefUtils.getBoolean(getApplicationContext(), "is_showed_guide", true)) {
//			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//			startActivity(intent);
//		}else{
			Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
			startActivity(intent);

//		}
		finish();
	}
	
	/**
	 * �����������ݿ⵽����Ŀ¼֮��
	 */
	private void copyDB() {

		try {
			File file = new File(getFilesDir(), "dictionary.db");

			// �ж��ļ��Ƿ����
			if (file.exists() && file.length() > 0) {
			} else {
				InputStream is = getAssets().open("dictionary.db");
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);

				}

				is.close();
				fos.close();
			}
		} catch (IOException e) {
			Toast.makeText(this, "��������", 0).show();
			e.printStackTrace();

		}

	}

}
