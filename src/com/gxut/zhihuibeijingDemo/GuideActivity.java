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
 * ��һ�δ򿪳��ֵ�����ҳ
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-26 ����3:09:50
 */
public class GuideActivity extends Activity {

	private ViewPager vpGuide;
	private static final int[] mImageIds = new int[] { R.drawable.dasai,
			R.drawable.help_1, R.drawable.help_2 };
	private ArrayList<ImageView> imageList;

	private LinearLayout guideLl;// ����СԲ������Բ���

	// СԲ��Ĵ�С
	int pxR;
	int dipR;

	private View guideRedPoint;// ��ɫСԲ��

	private int mPointWidth;// Բ���ľ���

	private Button guide_button;	//С��ť
	

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
	 * ��ʼ������
	 */
	private void initView() {
		// dipR=10;
		// pxR = DensityUtil.px2dip(this, dipR);

		// �����Щ����ͼƬ
		imageList = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(mImageIds[i]);
			imageList.add(imageView);
		}

		// ��ʼ��СԲ��
		for (int i = 0; i < mImageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.guide_point);

			// linearLayout�Ĳ��ַ�ʽ
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);
			if (i > 0) {
				params.leftMargin = 10;
			}
			point.setLayoutParams(params);
			guideLl.addView(point);
		}

		// ��ȡ��ͼ��, ��layout�����¼����м���
		guideLl.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					// ��layoutִ�н�����ص��˷���
					@Override
					public void onGlobalLayout() {
						System.out.println("layout ����");
						guideLl.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						mPointWidth = guideLl.getChildAt(1).getLeft()
								- guideLl.getChildAt(0).getLeft();
						System.out.println("Բ�����:" + mPointWidth);
					}
				});
	}

	/**
	 * viewPager��������
	 * 
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-26 ����3:33:51
	 */
	class MyGuidePagerAdapter extends PagerAdapter {

		// �м�����Ŀ
		@Override
		public int getCount() {
			return mImageIds.length;
		}

		// ��ȵ�ʱ��չʾ
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// ��ʼ�����ǵ�ҳ��,������getView��׼��ҳ��
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageList.get(position));
			return imageList.get(position);
		}

		// ɾ��view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			
		}
	}
	
	/**
	 * ҳ����ת������
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-26 ����10:08:38
	 */
	class MyOnPageChangeListener implements OnPageChangeListener{


		//����ҳ�汻ѡ�е�ʱ��
		@Override
		public void onPageSelected(int arg0) {
			if(arg0 != mImageIds.length - 1){
		
				guide_button.setVisibility(View.INVISIBLE);
			}else{
				guide_button.setVisibility(View.VISIBLE);
				//����Ķ���
				AlphaAnimation alphe = new AlphaAnimation(0, 1);
				alphe.setDuration(1000);//ʱ��
				alphe.setFillAfter(true);//�Ƿ񱣳ֶ���
				guide_button.startAnimation(alphe);
			}
		}

		// ������ʱ��
		//СԲ�������Ļ�ƶ�
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			int len = (int) (mPointWidth * arg1) + arg0 * mPointWidth;
			RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) guideRedPoint
					.getLayoutParams();
			layoutParams.leftMargin = len;
			guideRedPoint.setLayoutParams(layoutParams);

		}

		// ��ͼƬ�Ĺ���״̬�����ı��ʱ��
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	}
	
	/**
	 * ������ҳ��
	 */
	private void enterHome() {
		PrefUtils.setBoolean(getApplicationContext(), "is_showed_guide", true);
		Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
		startActivity(intent);
		finish();
	}
}
