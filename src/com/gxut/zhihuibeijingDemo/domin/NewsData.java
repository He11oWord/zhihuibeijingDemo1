package com.gxut.zhihuibeijingDemo.domin;

import java.util.ArrayList;

/**
 * 网页服务器下载下来的信息提供类
 * @author lizhao
 *
 */
public class NewsData {

	public int retcode;
	public ArrayList<NewsMenuData> data;
	
	//大的菜单
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
	
	//标签
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
