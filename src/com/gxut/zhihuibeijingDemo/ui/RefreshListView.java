package com.gxut.zhihuibeijingDemo.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gxut.zhihuibeijingDemo.R;
import com.lidroid.xutils.view.annotation.ViewInject;

public class RefreshListView extends ListView implements OnScrollListener,android.widget.AdapterView.OnItemClickListener{

	// �ж�״̬�ĳ���
	private static final int STATE_PULL_REFRESH = 1;
	private static final int STATE_RELESE_REFRESH = 2;
	private static final int STATE_REFRESH = 3;
	private int mCurrentState;

	private View mHeaderView;
	private int mHeaderViewHeight;
	private int startY;// ��ʼY
	private int paddingHeight;// ���ƾ���

	@ViewInject(R.id.referesh_tv_time)
	private TextView referesh_tv_time;

	@ViewInject(R.id.referesh_tv_title)
	private TextView referesh_tv_title;

	@ViewInject(R.id.referesh_iv_arr)
	private ImageView referesh_iv_arr;

	@ViewInject(R.id.referesh_pb_progress)
	private ProgressBar referesh_pb_progress;
	private RotateAnimation animDown;
	private RotateAnimation animUp;

	// �Ų���
	private View mFooterView;
	private int mFooterViewHeight;

	public RefreshListView(Context context) {
		super(context);
		initHeaderView();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeaderView();
		initFooterView();
	}

	/**
	 * ��ʼ��ͷ����
	 */
	private void initHeaderView() {
		mHeaderView = View.inflate(getContext(), R.layout.refresh_header, null);
		referesh_tv_title = (TextView) mHeaderView
				.findViewById(R.id.referesh_tv_title);
		referesh_tv_time = (TextView) mHeaderView
				.findViewById(R.id.referesh_tv_time);
		referesh_iv_arr = (ImageView) mHeaderView
				.findViewById(R.id.referesh_iv_arr);
		referesh_pb_progress = (ProgressBar) mHeaderView
				.findViewById(R.id.referesh_pb_progress);

		this.addHeaderView(mHeaderView);
		mHeaderView.measure(0, 0);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();

		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

		initArrowAnim();

	}

	/**
	 * ��ʼ���Ų���
	 */
	private void initFooterView() {
		mFooterView = View.inflate(getContext(),
				R.layout.refresh_listview_footer, null);

		this.addFooterView(mFooterView);
		mFooterView.measure(0, 0);
		mFooterViewHeight = mFooterView.getMeasuredHeight();

		mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);

		this.setOnScrollListener(this);
	}

	/**
	 * ���ô����¼�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getRawY();

			break;
		case MotionEvent.ACTION_MOVE:
			if (startY == -1) {
				startY = (int) ev.getRawY();
			}

			int endY = (int) ev.getRawY();
			int dy = endY - startY;

			if (dy > 0 && getFirstVisiblePosition() == 0) {
				paddingHeight = dy - mHeaderViewHeight;
				if (paddingHeight > 30)
					paddingHeight = 30;

				if (paddingHeight > 0 && mCurrentState != STATE_RELESE_REFRESH) {
					mCurrentState = STATE_RELESE_REFRESH;
					refresh();
				} else if (paddingHeight <= 0
						&& mCurrentState != STATE_PULL_REFRESH) {
					mCurrentState = STATE_PULL_REFRESH;
					refresh();
				}

				mHeaderView.setPadding(0, paddingHeight, 0, 0);

				return true;
			}

			break;
		case MotionEvent.ACTION_UP:
			startY = -1;
			//
			// if (paddingHeight == 0) {
			// Toast.makeText(getContext(), "ˢ��", 0).show();
			// }
			if (paddingHeight > 0 && mCurrentState == STATE_RELESE_REFRESH) {
				mCurrentState = STATE_REFRESH;
				mHeaderView.setPadding(0, 0, 0, 0);
				refresh();
			} else {
				mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

			}

			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * ��ʼ������Ч��
	 */
	private void refresh() {
		switch (mCurrentState) {
		case STATE_PULL_REFRESH:
			referesh_tv_title.setText("����ˢ��");
			referesh_iv_arr.startAnimation(animDown);
			referesh_iv_arr.setVisibility(View.VISIBLE);
			referesh_pb_progress.setVisibility(View.INVISIBLE);
			break;
		case STATE_RELESE_REFRESH:
			referesh_tv_title.setText("�ɿ�ˢ��");
			referesh_iv_arr.startAnimation(animUp);
			referesh_tv_title.setVisibility(View.VISIBLE);
			referesh_tv_time.setVisibility(View.VISIBLE);
			referesh_iv_arr.setVisibility(View.VISIBLE);
			referesh_pb_progress.setVisibility(View.INVISIBLE);
			break;
		case STATE_REFRESH:
			referesh_tv_title.setVisibility(View.INVISIBLE);
			referesh_tv_time.setVisibility(View.INVISIBLE);
			// referesh_tv_title.setText(SystemClock.)
			referesh_iv_arr.clearAnimation();
			referesh_iv_arr.setVisibility(View.INVISIBLE);
			referesh_pb_progress.setVisibility(View.VISIBLE);
			if (onListener != null) {
				onListener.onrefresh();
			}

			break;
		}
	}

	/**
	 * ��ͷ�Ķ���
	 */
	private void initArrowAnim() {
		// ��ͷ���ϵĶ���
		animUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		animUp.setDuration(200);
		animUp.setFillAfter(true);
		// ��ͷ���µĶ���
		animDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animDown.setDuration(200);
		animDown.setFillAfter(true);

	}

	
	/**
	 * ���������б�
	 */
	public void onRefreshComplete(boolean successed) {
		if (isLoadingMore) {
			mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
			isLoadingMore = false;
		} else {

			mCurrentState = STATE_PULL_REFRESH;
			referesh_tv_title.setText("����ˢ��");

			referesh_tv_title.setVisibility(View.VISIBLE);
			referesh_tv_time.setVisibility(View.VISIBLE);
			referesh_iv_arr.setVisibility(View.VISIBLE);
			referesh_pb_progress.setVisibility(View.INVISIBLE);
			mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
			if (successed) {
				referesh_tv_time.setText("������ʱ��" + getCurrentTime());
			}
		}
	}

	/**
	 * ��ȡ��ǰʱ��
	 */
	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

	//������һҳ
	private boolean isLoadingMore;
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE
				|| scrollState == SCROLL_STATE_FLING) {
			if (getLastVisiblePosition() == (getCount() - 1) && !isLoadingMore) {
				mFooterView.setPadding(0, 0, 0, 0);
				setSelection(getCount());// ��ʾListview��λ��
				isLoadingMore = true;
				
				if(isLoadingMore){
					onListener.onLoadMore();
				}
			}

		}
	}
	
	onRefreshListener onListener;

	public interface onRefreshListener {
		void onrefresh();
		void onLoadMore();
	}

	public void setOnRefreshListener(onRefreshListener onListener) {
		this.onListener = onListener;
	}
	
	//��д����¼��Ľӿ�
	OnItemClickListener mItemClickListener;
	@Override
	public void setOnItemClickListener(
			android.widget.AdapterView.OnItemClickListener listener) {
		super.setOnItemClickListener(this);
		mItemClickListener = listener;
		
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if(mItemClickListener!=null){
			//�ѵ������Ŀ���û�ԭ����
			mItemClickListener.onItemClick( arg0, arg1, arg2-getHeaderViewsCount(),arg3);
		}
	}
}
