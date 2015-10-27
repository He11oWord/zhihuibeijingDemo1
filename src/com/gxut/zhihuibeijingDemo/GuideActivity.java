package com.gxut.zhihuibeijingDemo;

import java.util.ArrayList;

import com.gxut.zhihuibeijingDemo.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 第一次打开出现的引导页
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-26 下午3:09:50
 */
public class GuideActivity extends Activity {

	private ViewPager vpGuide;
	private static final int[] mImageIds = new int[] { R.drawable.dasai,
			R.drawable.help_1, R.drawable.help_2 };
	private ArrayList<ImageView> imageList;

	private LinearLayout guideLl;// 三个小圆点的线性布局

	// 小圆点的大小
	int pxR;
	int dipR;

	private View guideRedPoint;// 红色小圆点

	private int mPointWidth;// 圆点间的距离

	private Button guide_button;	//小按钮
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		
		
		
		guide_button = (Button) findViewById(R.id.guide_button);
		guideRedPoint = findViewById(R.id.guide_red_point);
		guideLl = (LinearLayout) findViewById(R.id.guide_ll);
		initView();
		vpGuide = (ViewPager) findViewById(R.id.guide_vp);
		vpGuide.setAdapter(new MyGuidePagerAdapter());
		vpGuide.setOnPageChangeListener(new MyOnPageChangeListener());

	
		guide_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				enterHome();
			}

		
		});
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		// dipR=10;
		// pxR = DensityUtil.px2dip(this, dipR);

		// 存放那些背景图片
		imageList = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(mImageIds[i]);
			imageList.add(imageView);
		}

		// 初始化小圆点
		for (int i = 0; i < mImageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.guide_point);

			// linearLayout的布局方式
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);
			if (i > 0) {
				params.leftMargin = 10;
			}
			point.setLayoutParams(params);
			guideLl.addView(point);
		}

		// 获取视图树, 对layout结束事件进行监听
		guideLl.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					// 当layout执行结束后回调此方法
					@Override
					public void onGlobalLayout() {
						System.out.println("layout 结束");
						guideLl.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						mPointWidth = guideLl.getChildAt(1).getLeft()
								- guideLl.getChildAt(0).getLeft();
						System.out.println("圆点距离:" + mPointWidth);
					}
				});
	}

	/**
	 * viewPager的适配器
	 * 
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-26 下午3:33:51
	 */
	class MyGuidePagerAdapter extends PagerAdapter {

		// 有几个条目
		@Override
		public int getCount() {
			return mImageIds.length;
		}

		// 相等的时候展示
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 初始化我们的页面,类似于getView，准备页面
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageList.get(position));
			return imageList.get(position);
		}

		// 删除view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			
		}
	}
	
	/**
	 * 页面跳转监听者
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-26 下午10:08:38
	 */
	class MyOnPageChangeListener implements OnPageChangeListener{


		//整个页面被选中的时候
		@Override
		public void onPageSelected(int arg0) {
			if(arg0 != mImageIds.length - 1){
		
				guide_button.setVisibility(View.INVISIBLE);
			}else{
				guide_button.setVisibility(View.VISIBLE);
				//渐变的动画
				AlphaAnimation alphe = new AlphaAnimation(0, 1);
				alphe.setDuration(1000);//时间
				alphe.setFillAfter(true);//是否保持动画
				guide_button.startAnimation(alphe);
			}
		}

		// 滑动的时候
		//小圆点跟着屏幕移动
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			int len = (int) (mPointWidth * arg1) + arg0 * mPointWidth;
			RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) guideRedPoint
					.getLayoutParams();
			layoutParams.leftMargin = len;
			guideRedPoint.setLayoutParams(layoutParams);

		}

		// 当图片的滚动状态发生改变的时候
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	}
	
	/**
	 * 进入主页面
	 */
	private void enterHome() {
		PrefUtils.setBoolean(getApplicationContext(), "is_showed_guide", true);
		Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
		startActivity(intent);
		finish();
	}
}
