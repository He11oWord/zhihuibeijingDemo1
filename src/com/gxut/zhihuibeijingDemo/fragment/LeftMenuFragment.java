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
 * ��߳���˵��Ĳ���
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����12:26:56
 */
public class LeftMenuFragment extends BaseFragment {

	@ViewInject(R.id.lv_left_menu)
	private ListView lv_left;

	// ������
	private MyListAdapter adapter;

	private ArrayList<NewsMenuData> menuList;
	public NewsData newsData;
	private MainActivity mainUI;

	//���������Ŀ
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
				//��������ҳ����ʾ
				setDetailPager(arg2);
				//�ջز����
				toggleSlidingMenu();
			}
		});
		
	}


	
	
	/**
	 * �л��������״̬
	 */
	protected void toggleSlidingMenu() {
		mainUI = (MainActivity) mActivity;//���MainActivity
		mainUI.getSlidingMenu().toggle();
	}

	/**
	 * ��Ӧ4������ҳ��
	 * @param arg2
	 */
	protected void setDetailPager(int arg2) {
		mainUI = (MainActivity) mActivity;//���MainActivity
		ActivityFragment activityFragment = mainUI.getActivityFragment();//���ActivityF
		NewsPager newsPager = activityFragment.getNewsPager();//���NewsPager
		newsPager.setDetailPager(arg2);//����NewsPager������ҳ�淽��
		
	}

	/**
	 * ����json����
	 * @param fromJson
	 */
	public void setNewsData(NewsData fromJson) {
		menuList = fromJson.data;
		myListAdapter = new MyListAdapter();
		lv_left.setAdapter(myListAdapter);
	}

	/**
	 * �����ListView��������
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
