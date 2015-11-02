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
 * 主页的Fragment
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 上午12:32:29
 */
public class ActivityFragment extends BaseFragment {

	@ViewInject(R.id.fra_act_rg)
	public RadioGroup fra_act_rg;

	@ViewInject(R.id.fra_act_vp)
	public ViewPager fra_act_vp;

	// 适配器
	private MyGuidePagerAdapter mpa;

	// 基类的数组
	private List<BasePager> bpList;

	@Override
	public View initViews() {
		// 实例化基类数组
		bpList = new ArrayList<BasePager>();
		// 实例化适配器
		// mpa = ;

		View view = View.inflate(mActivity, R.layout.fragment_activity, null);
		// fra_act_rg = (RadioGroup) view.findViewById(R.id.fra_act_rg);
		// 用Xutil包能直接把所有的东西都找出来
		ViewUtils.inject(this, view);

		return view;
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {
		fra_act_rg.check(R.id.fra_act_btn_home);
		// 添加几个自定义的页面
		bpList.add(new HomePager(mActivity,this));
		bpList.add(new NewsPager(mActivity));
		bpList.add(new ServicePager(mActivity));
		bpList.add(new PersonPager(mActivity));

		// 预设置第一个页面的侧边栏可以使用
		bpList.get(0).initData();
		fra_act_vp.setAdapter(new MyGuidePagerAdapter());

		// 设置底top的点击事件
		fra_act_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.fra_act_btn_home:// 主页面
					fra_act_vp.setCurrentItem(0);
					break;
				case R.id.fra_act_btn_news:// 新闻
					fra_act_vp.setCurrentItem(1);
					break;
//				case 1:// 新闻
//					fra_act_vp.setCurrentItem(1);
//					break;
				case R.id.fra_act_btn_fuwu:// 服务
					fra_act_vp.setCurrentItem(2);
					break;
				case R.id.fra_act_btn_geren:// 个人
					fra_act_vp.setCurrentItem(3);
					break;
				}

			}
		});
		/**
		 * 控制侧边栏的出现
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
	 * viewPager的适配器
	 * 
	 * @Description TODO
	 * @author lizhao
	 * @date 2015-10-27 下午6:32:10
	 */
	class MyGuidePagerAdapter extends PagerAdapter {

		// 有几个条目
		@Override
		public int getCount() {
			return bpList.size();
		}

		// 相等的时候展示
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 初始化我们的页面,类似于getView，准备页面
		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			BasePager pager = bpList.get(position);
			container.addView(pager.mView);
			return bpList.get(position).mView;
		}

		// 删除view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

		}
	}

	/**
	 * 获得NewsPager
	 * 
	 * @return
	 */
	public NewsPager getNewsPager() {
		return (NewsPager) bpList.get(1);
	}
}
