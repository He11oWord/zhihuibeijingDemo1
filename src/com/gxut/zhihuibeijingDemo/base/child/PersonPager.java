package com.gxut.zhihuibeijingDemo.base.child;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
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

/**
 * ����ҳ��Ĳ��֣��ɶ�̬����
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����9:58:56
 */
public class PersonPager extends BasePager implements OnItemClickListener,
		OnClickListener {

	private Button button_person;
	private ListView lv_person;
	private MyPersonListAdapter myPersonListAdapter;
	private String[] person_lv_text = new String[] { "��������", "�����¼", "�����ղ�",
			"�Ķ�ģʽ", "����ģʽ", "��������", "�������", "�������", "����°汾", "����" };
	private int[] ids = { R.drawable.icon_setting_bound,
			R.drawable.icon_setting_bound, R.drawable.icon_setting_good,
			R.drawable.icon_setting_read, R.drawable.icon_setting_book,
			R.drawable.icon_setting_push, R.drawable.icon_setting_clear_cache,
			R.drawable.icon_setting_feedback,
			R.drawable.icon_setting_checkversion, R.drawable.icon_setting_info };

	public PersonPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initView() {
		super.initView();
		View person_view = View.inflate(mActivity, R.layout.pager_person, null);
		button_person = (Button) person_view.findViewById(R.id.person_button);
		lv_person = (ListView) person_view.findViewById(R.id.person_lv);
		mView = person_view;
	}

	@Override
	public void initData() {
		setSlidingMenu(false);
		button_person.setOnClickListener(this);
		lv_person.setAdapter(new MyPersonListAdapter());

		lv_person.setOnItemClickListener(this);

	}

	/**
	 * ���������lv������
	 */
	class MyPersonListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View lv_view = View.inflate(mActivity,
					R.layout.person_pager_lv_item, null);
			ImageView iv = (ImageView) lv_view
					.findViewById(R.id.person_pager_iv);
			TextView tv = (TextView) lv_view.findViewById(R.id.person_pager_tv);
			iv.setImageResource(ids[arg0]);
			tv.setText(person_lv_text[arg0]);
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
	 * ��½��ť�ĵ����ť
	 */

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(mActivity, LoginActivity.class);
		mActivity.startActivity(intent);
	}

	/**
	 * �����б�ĵ����ť
	 */

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent;
		switch (position) {
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
			Toast.makeText(mActivity, "�Ѿ������°汾", 0).show();
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
