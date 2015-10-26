package edu.gxut.table;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<String> packageNames;
	private MyListAdapter adapter;
	private PackageManager pm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pm = getPackageManager();
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.LAUNCHER");
		List<ResolveInfo> queryIntentActivities = pm.queryIntentActivities(
				intent, PackageManager.GET_INTENT_FILTERS);
		packageNames = new ArrayList<String>();
		for (ResolveInfo r : queryIntentActivities) {
			packageNames.add(r.activityInfo.packageName);
		}
		Log.d("1", packageNames.size() + "");
		GridView lv = (GridView) findViewById(R.id.lv);
		adapter = new MyListAdapter();
		lv.setAdapter(adapter);

	}

	class MyListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return packageNames.size();

		}

	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(getApplicationContext(), R.layout.home_item_layout, null);
			ImageView iv = (ImageView) view.findViewById(R.id.item_iv);
			TextView tv = (TextView) view.findViewById(R.id.item_tv);
			
			try {
				iv.setImageDrawable(pm.getApplicationInfo(packageNames.get(position), 0).loadIcon(pm));
				tv.setText(pm.getApplicationInfo(packageNames.get(position), 0).loadLabel(pm));
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			// TextView tv = new TextView(getApplicationContext());
			// tv.setText(packageNames.get(position));
			// //tv.setText("111");
			// tv.setTextColor(Color.BLACK);
			return view;
		}

		
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
