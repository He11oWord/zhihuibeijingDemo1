package com.gxut.zhihuibeijingDemo.domin;

import java.util.ArrayList;

/**
 * ��ͼjson�ַ���������������Ϣ�ṩ��
 */
public class ZutuData {
	public int retcode;
	public ZutuInfo data;

	/**
	 * data��Ϣ
	 */
	public class ZutuInfo {
		public String countcommenturl;
		public String more;
		public String title;
		public ArrayList<ZutuNewsInfo> news;

	}
	/**
	 * ������Ϣ
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
