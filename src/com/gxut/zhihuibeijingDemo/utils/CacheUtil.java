package com.gxut.zhihuibeijingDemo.utils;

import android.content.Context;

/**
 * 加载缓存的工具类
 */
public class CacheUtil {
	
	//设置缓存数据
	public static void setCache(Context context,String key,String value){
		PrefUtils.setString(context, key, value);
	}
	//获得缓存数据
	public static String getCache(Context context,String key){
		return PrefUtils.getString(context, key,null);
	}
	
}
