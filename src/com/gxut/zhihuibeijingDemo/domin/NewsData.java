package com.gxut.zhihuibeijingDemo.domin;

import java.util.ArrayList;

/**
 * ��ҳ������������������Ϣ�ṩ��
 * @author lizhao
 *
 */
public class NewsData {

	public int retcode;
	public ArrayList<NewsMenuData> data;
	
	//��Ĳ˵�
	public class NewsMenuData{
		public String id;
		public String title;
		public int type;
		public String url;
		
		public ArrayList<NewsTabData> children;

		@Override
		public String toString() {
			return "NewsMenuData [" + ", title=" + title +", url=" + url + ", children=" + children + "]";
		}
	}
	
	//��ǩ
	public class NewsTabData{
		public String id;
		public String title;
		public int type;
		public String url;
		@Override
		public String toString() {
			return "NewsTabData [" + ", title=" + title 
				+ ", url=" + url + "]";
		}
		
	}

	@Override
	public String toString() {
		return "NewsData [data=" + data + "]";
	}
	
	
}
