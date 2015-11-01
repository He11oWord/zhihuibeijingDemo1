package com.gxut.zhihuibeijingDemo.fragment;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.MainActivity;
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.child.NewsPager;
import com.gxut.zhihuibeijingDemo.domin.NewsData;
import com.gxut.zhihuibeijingDemo.domin.NewsData.NewsMenuData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 侧边抽屉菜单的布局
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 上午12:26:56
 */
public class LeftMenuFragment extends BaseFragment {

	@ViewInject(R.id.lv_left_menu)
	private ListView lv_left;

	// 适配器
	private MyListAdapter adapter;

	private ArrayList<NewsMenuData> menuList;
	public NewsData newsData;
	private MainActivity mainUI;

	//被点击的条目
	public int mPosition = 0;

	private MyListAdapter myListAdapter;
	
	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
	
		lv_left.setOnItemClickListener(new OnItemClickListener() {
		
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mPosition = arg2;
				myListAdapter.notifyDataSetChanged();
				//设置详情页的显示
				setDetailPager(arg2);
				//收回侧边栏
				toggleSlidingMenu();
			}
		});
		
	}


	
	
	/**
	 * 切换侧边栏的状态
	 */
	protected void toggleSlidingMenu() {
		mainUI = (MainActivity) mActivity;//获得MainActivity
		mainUI.getSlidingMenu().toggle();
	}

	/**
	 * 响应4个详情页面
	 * @param arg2
	 */
	protected void setDetailPager(int arg2) {
		mainUI = (MainActivity) mActivity;//获得MainActivity
		ActivityFragment activityFragment = mainUI.getActivityFragment();//获得ActivityF
		NewsPager newsPager = activityFragment.getNewsPager();//获得NewsPager
		newsPager.setDetailPager(arg2);//调用NewsPager的设置页面方法
		
	}

	/**
	 * 设置json数据
	 * @param fromJson
	 */
	public void setNewsData(NewsData fromJson) {
		menuList = fromJson.data;
		myListAdapter = new MyListAdapter();
		lv_left.setAdapter(myListAdapter);
	}

	/**
	 * 侧边栏ListView的适配器
	 * @author lizhao
	 * 
	 */
	class MyListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return menuList.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mActivity, R.layout.left_lv_item, null);
			TextView lv_left_menu_tv = (TextView) view
					.findViewById(R.id.lv_left_menu_tv);
			lv_left_menu_tv.setText(menuList.get(position).title);
	
			
			if(mPosition == position){
				lv_left_menu_tv.setEnabled(false);
			}else{
				lv_left_menu_tv.setEnabled(true);
			}
			
			
			return view;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

	}
	
	public int getMPostion(){
		return mPosition;
	}
	

}
