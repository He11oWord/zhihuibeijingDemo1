package com.gxut.zhihuibeijingDemo.base.child;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BasePager;
import com.gxut.zhihuibeijingDemo.settingactiivty.LoginActivity;
import com.gxut.zhihuibeijingDemo.settingactiivty.OtherActivity;
import com.gxut.zhihuibeijingDemo.settingactiivty.ReadModeActivity;
import com.gxut.zhihuibeijingDemo.settingactiivty.SuggestionActivity;
import com.gxut.zhihuibeijingDemo.settingactiivty.TextSizeActivity;
import com.gxut.zhihuibeijingDemo.utils.PrefUtils;

/**
 * 个人页面的布局，可动态设置
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 下午9:58:56
 */
public class PersonPager extends BasePager implements OnItemClickListener,
		OnClickListener {

	private Button button_person;
	private ListView lv_person;
	private MyPersonListAdapter myPersonListAdapter;
	private String[] person_lv_text = new String[] { 
//			"离线下载", "浏览记录", "个人收藏",
			
			"阅读模式", "正文模式", "推送设置", "清除缓存", "意见反馈", "检测新版", "关于软件" };
	private int[] ids = { 
//			R.drawable.icon_setting_bound,
//			R.drawable.icon_setting_bound, R.drawable.icon_setting_good,
			R.drawable.icon_setting_read, R.drawable.icon_setting_book,
			R.drawable.icon_setting_push, R.drawable.icon_setting_clear_cache,
			R.drawable.icon_setting_feedback,
			R.drawable.icon_setting_checkversion, R.drawable.icon_setting_info };
	private ImageButton iv;
	private TextView person_tv;

	public PersonPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		super.initView();
		View person_view = View.inflate(mActivity, R.layout.pager_person, null);
		button_person = (Button) person_view.findViewById(R.id.person_button);
		person_tv = (TextView) person_view.findViewById(R.id.person_tv);
		lv_person = (ListView) person_view.findViewById(R.id.person_lv);
		iv = (ImageButton) person_view.findViewById(R.id.person_iv);
		mView = person_view;
	}

	@Override
	public void initData() {
		setSlidingMenu(false);
		if(PrefUtils.getBoolean(mActivity, "isLogin", false)){
			button_person.setVisibility(View.INVISIBLE);
			person_tv.setVisibility(View.INVISIBLE);
			iv.setVisibility(View.VISIBLE);
		}else{
			button_person.setVisibility(View.VISIBLE);
			person_tv.setVisibility(View.VISIBLE);
			iv.setVisibility(View.INVISIBLE);
		}
		
		
		
		button_person.setOnClickListener(this);
		iv.setOnClickListener(this);
		lv_person.setAdapter(new MyPersonListAdapter());

		lv_person.setOnItemClickListener(this);

	}

	/**
	 * 个人里面的lv适配器
	 */
	class MyPersonListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View lv_view = View.inflate(mActivity,
					R.layout.person_pager_lv_item, null);
			ImageView iv = (ImageView) lv_view
					.findViewById(R.id.person_pager_iv);
			TextView tv = (TextView) lv_view.findViewById(R.id.person_pager_tv);
			iv.setImageResource(ids[arg0]);
			tv.setText(person_lv_text[arg0]);tv.setTextColor(Color.BLACK);
			return lv_view;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
	}

	/**
	 * 登陆按钮的点击按钮
	 */

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.person_button:
			Intent intent = new Intent(mActivity, LoginActivity.class);
			mActivity.startActivity(intent);
			break;
		case R.id.person_iv:
			PrefUtils.setBoolean(mActivity, "isLogin", false);
			//Toast.makeText(mActivity, "退出将在下次重启后成功", 0).show();
			this.initData();
			break;
		}
		
	}


	/**
	 * 设置列表的点击按钮
	 */

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent;
		switch (position+3) {
		case 0:
			break;
		case 1:
			break;
		case 3:
			intent = new Intent(mActivity,ReadModeActivity.class);
			mActivity.startActivity(intent);
			break;
		case 4:
			intent = new Intent(mActivity,TextSizeActivity.class);
			mActivity.startActivity(intent);
			break;
		case 6:
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Toast.makeText(mActivity, "清除完成", 0).show();
			break;
			
		case 7:
			intent = new Intent(mActivity,SuggestionActivity.class);
			mActivity.startActivity(intent);
			break;
		case 8:
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Toast.makeText(mActivity, "已经是最新版本", 0).show();
			break;
		case 9:
			intent = new Intent(mActivity,OtherActivity.class);
			mActivity.startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	
	
}
