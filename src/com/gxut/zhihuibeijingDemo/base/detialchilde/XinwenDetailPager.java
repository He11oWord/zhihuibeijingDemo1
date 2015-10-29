package com.gxut.zhihuibeijingDemo.base.detialchilde;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.base.BasePager;
import com.gxut.zhihuibeijingDemo.domin.NewsData.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

/**
 * ����-���ŵ����ݣ��ɶ�̬����
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:26
 */
public class XinwenDetailPager extends BaseDetailPager {

	private ViewPager vp;
	private MyPagerAdapter adapter;
	private XinwenChilderDetailPager xcdp;
	private ArrayList<NewsTabData> childrenList;// ��������
	private ArrayList<XinwenChilderDetailPager> xcdlList;
	private TabPageIndicator mIndicator;

	public XinwenDetailPager(Activity activity, ArrayList<NewsTabData> children) {
		super(activity);
		childrenList = children;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.base_pager_news_menu_tab,
				null);
		vp = (ViewPager) view.findViewById(R.id.menu_tab_vp);
		
		//��ʼ��
		mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);

		// mViewPager.setOnPageChangeListener(this);//ע��:��viewpager��Indicator��ʱ,
		// ����������Ҫ���ø�Indicator������viewpager

		return view;
	}

	@Override
	public void initData() {
		adapter = new MyPagerAdapter();
		xcdlList = new ArrayList<XinwenChilderDetailPager>();
		for (int i = 0; i < childrenList.size(); i++) {
			xcdlList.add(new XinwenChilderDetailPager(mActivity, childrenList
					.get(i)));
		}

		vp.setAdapter(adapter);
		mIndicator.setViewPager(vp);//��viewPager����֮����ص��������
	}

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return xcdlList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(xcdlList.get(position).mView);
			xcdlList.get(position).initData();
			return xcdlList.get(position).mView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		public CharSequence getPageTitle(int position) {
			return childrenList.get(position).title;

		}

	}

}
