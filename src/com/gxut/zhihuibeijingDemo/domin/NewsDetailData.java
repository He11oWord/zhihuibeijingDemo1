package com.gxut.zhihuibeijingDemo.domin;

import java.util.ArrayList;

/**
 * 新闻详情页，页签的信息类
 * 
 * @author lizhao
 * 
 */
public class NewsDetailData {
	public int retcode;
	public NewsDetailChilrenData data;

	public class NewsDetailChilrenData {
		public String countcommenturl;
		public String more;
		public ArrayList<NewsDetailChilrenNewsData> news;
		public ArrayList<NewsDetailChilrentopicData> topic;
		public ArrayList<NewsDetailChilrentopnewsData> topnews;

		@Override
		public String toString() {
			return "NewsDetailChilrenData [countcommenturl=" + countcommenturl
					+ ", more=" + more + ", news=" + news + ", topic=" + topic
					+ ", topnews=" + topnews + "]";
		}

	}

	// 对应news
	public class NewsDetailChilrenNewsData {
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public String id;
		public String listimage;
		public String pubdate;
		public String title;
		public String type;
		public String url;

		@Override
		public String toString() {
			return "NewsDetailChilrenNewsData [comment=" + comment
					+ ", listimage=" + listimage + ", pubdate=" + pubdate + "]";
		}

	}

	// 对应topic
	public class NewsDetailChilrentopicData {
		public String description;
		public String id;
		public String listimage;
		public String title;
		public String url;
		public int sort;

		@Override
		public String toString() {
			return "NewsDetailChilrentopicData [description=" + description
					+ ", id=" + id + ", listimage=" + listimage + ", title="
					+ title + ", url=" + url + ", sort=" + sort + "]";
		}

	}

	// 对应topnews
	public class NewsDetailChilrentopnewsData {
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
			return "NewsDetailChilrentopnewsData [comment=" + comment
					+ ", pubdate=" + pubdate + ", topimage=" + topimage
					+ ", title=" + title + "]";
		}

	}

	@Override
	public String toString() {
		return "NewsDetailData [retcode=" + retcode + ", data=" + data + "]";
	}

}
