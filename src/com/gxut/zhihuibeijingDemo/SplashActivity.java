package com.gxut.zhihuibeijingDemo;

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

/**
 * SplashActivity页面
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-26 下午2:35:06
 */
public class SplashActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	
		RelativeLayout relative = (RelativeLayout) findViewById(R.id.splash_relative);
		startAnimation(relative);

	}

	/**
	 * 播放动画 旋转/缩放/渐变
	 * 
	 * @param relative
	 */
	private void startAnimation(RelativeLayout relative) {
		// 装动画的容器
		AnimationSet set = new AnimationSet(false);

		// 旋转的动画
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(1000);// 时间
		rotate.setFillAfter(true);// 是否保持动画

		// 缩放的动画
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
				0.3f);
		scale.setDuration(1000);// 时间
		scale.setFillAfter(true);// 是否保持动画

		// 渐变的动画
		AlphaAnimation alphe = new AlphaAnimation(0, 1);
		alphe.setDuration(1000);// 时间
		alphe.setFillAfter(true);// 是否保持动画

		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alphe);

		set.setAnimationListener(new AnimationListener() {

			// 动画开始的时候
			@Override
			public void onAnimationStart(Animation animation) {
			}

			// 播放的时候
			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			// 动画结束
			@Override
			public void onAnimationEnd(Animation animation) {
				// 进入引导页面或者主页面
				nextActivity();
			}

		});
		relative.startAnimation(set);
	}

	/**
	 * 根据Shared 进入引导页面或者主页面
	 */
	private void nextActivity() {

		if (PrefUtils.getBoolean(getApplicationContext(), "is_showed_guide", false)) {
			Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
			startActivity(intent);
		}else{
			Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
			startActivity(intent);

		}
		finish();
	}

}
