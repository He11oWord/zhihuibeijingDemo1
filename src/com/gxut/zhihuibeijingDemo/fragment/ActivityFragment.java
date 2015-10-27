package com.gxut.zhihuibeijingDemo.fragment;

import com.gxut.zhihuibeijingDemo.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * 主页的Fragment
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 上午12:32:29
 */
public class ActivityFragment extends BaseFragment {

	@ViewInject(R.id.fra_act_rg)
	private RadioGroup fra_act_rg;

	@ViewInject(R.id.fra_act_rg)
	private ViewPager fra_act_vp;
	
	private MyGuidePagerAdapter mpa;

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_activity, null);
		// fra_act_rg = (RadioGroup) view.findViewById(R.id.fra_act_rg);
		// 用Xutil包能直接把所有的东西都找出来
		ViewUtils.inject(this, view);
		fra_act_vp.setAdapter(mpa);
		return view;
	}

	@Override
	public void initData() {
		fra_act_rg.check(R.id.fra_act_btn_home);
	}

	/**
	 * viewPager的适配器
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-27 下午6:32:10
	 */
	class MyGuidePagerAdapter extends PagerAdapter {

		// 有几个条目
		@Override
		public int getCount() {
			return 4 ;
		}

		// 相等的时候展示
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 初始化我们的页面,类似于getView，准备页面
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//container.addView(imageList.get(position));
		//	return imageList.get(position);
			return null ;
		}

		// 删除view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

		}
	}

}
