package com.gxut.zhihuibeijingDemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment�Ļ���
 * @Description TODO
 * @author lizhao
 * @date 2015-10-27 ����12:16:08
 */
public abstract class BaseFragment extends Fragment {

	public Activity mActivity;

	//�ڻ��������ǵ���
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	//fragment������ʱ�����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	//��������Ĳ��֣������˵��Ҫ
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initViews();
	}

	/**
	 * ��ʼ������
	 */
	public abstract View initViews();
	
	/**
	 * ��ʼ������
	 */
	public void initData(){
		
	}
}
