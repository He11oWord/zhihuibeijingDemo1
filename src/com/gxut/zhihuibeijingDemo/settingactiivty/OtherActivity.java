package com.gxut.zhihuibeijingDemo.settingactiivty;
	
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
	
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseActivity;
	
/**
 * 关于
 * @author lizhao
 *
 */
public class OtherActivity extends BaseActivity {
	
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View initView() {
		View view = View.inflate(this, R.layout.base_activity_guanyu, null);
		tv = (TextView) view.findViewById(R.id.tv);
		return view;
	}
	@Override
	public void initData() {
		tv_title.setText("关于");
		tv.setText("智慧北京 v"+getVersion()+".0.2");
		
	}
	/**
	 * 获得当前的版本号
	 * @return
	 */
	private int getVersion() {
		PackageManager pm = getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
			int version = info.versionCode;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
}	
	
	
	