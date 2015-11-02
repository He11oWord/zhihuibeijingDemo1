package com.gxut.zhihuibeijingDemo.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BasePager;
import com.gxut.zhihuibeijingDemo.base.child.HomePager;
import com.gxut.zhihuibeijingDemo.base.child.NewsPager;
import com.gxut.zhihuibeijingDemo.base.child.PersonPager;
import com.gxut.zhihuibeijingDemo.base.child.ServicePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * ��ҳ��Fragment
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����12:32:29
 */
public class ActivityFragment extends BaseFragment {

	@ViewInject(R.id.fra_act_rg)
	public RadioGroup fra_act_rg;

	@ViewInject(R.id.fra_act_vp)
	public ViewPager fra_act_vp;

	// ������
	private MyGuidePagerAdapter mpa;

	// ���������
	private List<BasePager> bpList;

	@Override
	public View initViews() {
		// ʵ������������
		bpList = new ArrayList<BasePager>();
		// ʵ����������
		// mpa = ;

		View view = View.inflate(mActivity, R.layout.fragment_activity, null);
		// fra_act_rg = (RadioGroup) view.findViewById(R.id.fra_act_rg);
		// ��Xutil����ֱ�Ӱ����еĶ������ҳ���
		ViewUtils.inject(this, view);

		return view;
	}

	/**
	 * ��ʼ������
	 */
	@Override
	public void initData() {
		fra_act_rg.check(R.id.fra_act_btn_home);
		// ��Ӽ����Զ����ҳ��
		bpList.add(new HomePager(mActivity,this));
		bpList.add(new NewsPager(mActivity));
		bpList.add(new ServicePager(mActivity));
		bpList.add(new PersonPager(mActivity));

		// Ԥ���õ�һ��ҳ��Ĳ��������ʹ��
		bpList.get(0).initData();
		fra_act_vp.setAdapter(new MyGuidePagerAdapter());

		// ���õ�top�ĵ���¼�
		fra_act_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.fra_act_btn_home:// ��ҳ��
					fra_act_vp.setCurrentItem(0);
					break;
				case R.id.fra_act_btn_news:// ����
					fra_act_vp.setCurrentItem(1);
					break;
//				case 1:// ����
//					fra_act_vp.setCurrentItem(1);
//					break;
				case R.id.fra_act_btn_fuwu:// ����
					fra_act_vp.setCurrentItem(2);
					break;
				case R.id.fra_act_btn_geren:// ����
					fra_act_vp.setCurrentItem(3);
					break;
				}

			}
		});
		/**
		 * ���Ʋ�����ĳ���
		 */
		fra_act_vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				bpList.get(arg0).initData();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	/**
	 * viewPager��������
	 * 
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-27 ����6:32:10
	 */
	class MyGuidePagerAdapter extends PagerAdapter {

		// �м�����Ŀ
		@Override
		public int getCount() {
			return bpList.size();
		}

		// ��ȵ�ʱ��չʾ
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// ��ʼ�����ǵ�ҳ��,������getView��׼��ҳ��
		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			BasePager pager = bpList.get(position);
			container.addView(pager.mView);
			return bpList.get(position).mView;
		}

		// ɾ��view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

		}
	}

	/**
	 * ���NewsPager
	 * 
	 * @return
	 */
	public NewsPager getNewsPager() {
		return (NewsPager) bpList.get(1);
	}
}
