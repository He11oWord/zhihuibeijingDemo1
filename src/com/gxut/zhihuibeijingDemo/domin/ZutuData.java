package com.gxut.zhihuibeijingDemo.domin;

import java.util.ArrayList;

/**
 * 组图json字符串解析出来的信息提供类
 */
public class ZutuData {
	public int retcode;
	public ZutuInfo data;

	/**
	 * data信息
	 */
	public class ZutuInfo {
		public String countcommenturl;
		public String more;
		public String title;
		public ArrayList<ZutuNewsInfo> news;

	}
	/**
	 * 新闻信息
	 */
	public class ZutuNewsInfo {
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public String  listimage;
		public String id;
		public String pubdate;
		public String topimage;
		public String title;
		public String type;
		public String url;
	}
}
