package com.gxut.zhihuibeijingDemo.domin;

import java.util.ArrayList;

/**
 * 首页头条图片的信息提供类
 * @author lizhao
 */
public class HomeNewsData {
	public int retcode;
	public Homedata data;
	
	public class Homedata{
		public ArrayList<HomeTopData> topnews;

		@Override
		public String toString() {
			return "Homedata [topnews=" + topnews + "]";
		}
		
	}
	
	public class HomeTopData {
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public String id;
		public String pubdate;
		public String topimage;
		public String title;
		public String type;
		public String url;
		@Override
		public String toString() {
			return "HomeTopData [pubdate=" + pubdate + ", topimage=" + topimage
					+ ", title=" + title + "]";
		}
		
	}

	@Override
	public String toString() {
		return "HomeNewsData [retcode=" + retcode + ", data=" + data + "]";
	}
	
}
