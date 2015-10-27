package com.gxut.zhihuibeijingDemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference的封装类
 * @author Kevin
 * 
 */
public class PrefUtils {

	public static final String PREF_NAME = "config";

	/**
	 * 获取里面的值
	 * @param 上下文
	 * @param key key
	 * @param defaultValue 默认值
	 * @return
	 */
	public static boolean getBoolean(Context ctx, String key,
			boolean defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}
	/**
	 * 设置里面的值
	 * @param ctx 上下文
	 * @param key
	 * @param value 值
	 */
	public static void setBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
}
