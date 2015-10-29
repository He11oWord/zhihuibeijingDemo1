package com.gxut.zhihuibeijingDemo.base.detialchilde;

import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gxut.zhihuibeijingDemo.R;
import com.gxut.zhihuibeijingDemo.base.BaseDetailPager;
import com.gxut.zhihuibeijingDemo.domin.NewsData.NewsTabData;
import com.gxut.zhihuibeijingDemo.domin.NewsDetailData;
import com.gxut.zhihuibeijingDemo.domin.NewsDetailData.NewsDetailChilrenNewsData;
import com.gxut.zhihuibeijingDemo.global.GlobalUrl;
import com.gxut.zhihuibeijingDemo.ui.RefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

public class XinwenChilderDetailPager extends BaseDetailPager implements
		OnPageChangeListener {

	private NewsTabData newsTabData;// �������Ľ��
	private TextView tv_title;// ͷ������
	private NewsDetailData detailChilrenData;// ���������Ľ��
	private ViewPager detailVp;// ����ͷ����������ͼƬ
	private BitmapUtils bitmapUtils;// ����ͷ�����ŵ�ͼƬ
	private CirclePageIndicator mIndicator;// ����ͷ�����ŵ�СԲ��
	private RefreshListView top_list;
	private MyTopListAdapter myTopListAdapter;
	private List<NewsDetailChilrenNewsData> newsList;

	public XinwenChilderDetailPager(Activity activity, NewsTabData newsTabData1) {
		super(activity);
		mView = initView();
		newsTabData = newsTabData1;
	}

	@Override
	public View initView() {
		// text = new TextView(mActivity);
		// text.setText("����������");
		// text.setTextColor(Color.RED);
		// text.setTextSize(25);
		// text.setGravity(Gravity.CENTER);
		View view = View.inflate(mActivity, R.layout.news_detail, null);
		View headView = View.inflate(mActivity, R.layout.top_headview, null);
		detailVp = (ViewPager) headView.findViewById(R.id.news_detail_vp);
		tv_title = (TextView) headView.findViewById(R.id.tv_title);
		mIndicator = (CirclePageIndicator) headView.findViewById(R.id.indicator);
		top_list = (RefreshListView) view.findViewById(R.id.news_detail_lv);
		top_list.addHeaderView(headView);
		return view;
	}

	@Override
	public void initData() {

		// �ӷ������������
		getDataFromServer();

	}

	/**
	 * �ӷ������������
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		System.out.println(GlobalUrl.CATEGORIES_URL + newsTabData.url);

		String mUrl = GlobalUrl.SERVER_URL + newsTabData.url;
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			// ���ʳɹ�
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			// ����ʧ��
			@Override
			public void onFailure(HttpException error, String msg) {
				Log.d("NewsPager", "ʧ��" + msg);

			}
		});
	}

	/**
	 * ��ȡjson����
	 * 
	 * @param result
	 */
	protected void parseData(String result) {
		Gson gson = new Gson();
		NewsDetailData fromJson = gson.fromJson(result, NewsDetailData.class);
		detailChilrenData = fromJson;
		System.out.println("�������" + detailChilrenData);

		if (detailChilrenData.data.topnews != null) {
			detailVp.setAdapter(new MyChildPagerAdapter());
			mIndicator.setViewPager(detailVp);
			mIndicator.setSnap(true);// ������ʾ
			mIndicator.setOnPageChangeListener(this);
			mIndicator.onPageSelected(0);
			tv_title.setText(detailChilrenData.data.topnews.get(0).title);
		}

		newsList = detailChilrenData.data.news;

		if (newsList != null) {
			myTopListAdapter = new MyTopListAdapter();
			top_list.setAdapter(myTopListAdapter);
		}

	}

	/**
	 * ͷ��viewPager������
	 * 
	 * @author lizhao
	 * 
	 */
	class MyChildPagerAdapter extends PagerAdapter {

		public MyChildPagerAdapter() {
			bitmapUtils = new BitmapUtils(mActivity);
			bitmapUtils.configDefaultLoadingImage(R.drawable.home_scroll_default);

		}

		@Override
		public int getCount() {
			return detailChilrenData.data.topnews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = new ImageView(mActivity);
			iv.setImageResource(R.drawable.e);
			iv.setScaleType(ScaleType.FIT_XY);
			container.addView(iv);
			bitmapUtils.display(iv,
					detailChilrenData.data.topnews.get(position).topimage);
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	/**
	 * listView��������
	 */
	class MyTopListAdapter extends BaseAdapter {

		private BitmapUtils listBitmapUtils;

		public MyTopListAdapter() {
			listBitmapUtils = new BitmapUtils(mActivity);
			listBitmapUtils.configDefaultLoadingImage(R.drawable.home_scroll_default);
		}

		@Override
		public int getCount() {
			return newsList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.list_news_item,
						null);
				holder = new ViewHolder();
				holder.tv1 = (TextView) convertView.findViewById(R.id.tv_title);
				holder.tv2 = (TextView) convertView.findViewById(R.id.tv_date);
				holder.iv = (ImageView) convertView.findViewById(R.id.iv_pic);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NewsDetailChilrenNewsData newsDetailChilrenNewsData = newsList
					.get(position);
			holder.tv1.setText(newsDetailChilrenNewsData.title);
			holder.tv2.setText(newsDetailChilrenNewsData.pubdate);
			listBitmapUtils.display(holder.iv,
					newsDetailChilrenNewsData.listimage);

			return convertView;
		}

	}

	class ViewHolder {
		public TextView tv1;
		public TextView tv2;
		public ImageView iv;
	}

	@Override
	public void onPageSelected(int arg0) {
		tv_title.setText(detailChilrenData.data.topnews.get(arg0).title);
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}
}
