package com.gxut.zhihuibeijingDemo.utils;

import android.content.Context;

/**
 * ���ػ���Ĺ�����
 */
public class CacheUtil {
	
	//���û�������
	public static void setCache(Context context,String key,String value){
		PrefUtils.setString(context, key, value);
	}
	//��û�������
	public static String getCache(Context context,String key){
		return PrefUtils.getString(context, key,null);
	}
	
}
