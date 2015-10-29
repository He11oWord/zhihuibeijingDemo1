package com.gxut.zhihuibeijingDemo.ui;

import com.gxut.zhihuibeijingDemo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class RefreshListView extends ListView {

	private View mHeaderView;
	public RefreshListView(Context context) {
		super(context);
		initHeaderView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
	}

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeaderView();
	}
	/**
	 * 初始化头布局
	 */
	private void initHeaderView() {
		mHeaderView = View.inflate(getContext(), R.layout.refresh_header, null);
		this.addHeaderView(mHeaderView);
		mHeaderView.measure(0, 0);
		int mviewHeight = mHeaderView.getMeasuredHeight();
		
		mHeaderView.setPadding(0, -mviewHeight, 0, 0);
	}

}
