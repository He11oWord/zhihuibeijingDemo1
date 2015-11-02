package com.gxut.zhihuibeijingDemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference的封装类
 * 
 * @author Kevin
 * 
 */
public class PrefUtils {

	public static final String PREF_NAME = "config";

	/**
	 * 获取里面的值
	 * 
	 * @param 上下文
	 * @param key
	 *            key
	 * @param defaultValue
	 *            默认值
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
	 * 
	 * @param ctx
	 *            上下文
	 * @param key
	 * @param value
	 *            值
	 */
	public static void setBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
	
	/**
	 * 设置String类型的值
	 * @param ctx
	 * @param key
	 * @param s
	 */
	public static void setString(Context ctx, String key, String s) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putString(key, s).commit();
	}

	public static String getString(Context ctx, String key,String defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}
	
	/**
	 * 设置int类型的值
	 * @param ctx
	 * @param key
	 * @param s
	 */
	public static void setInt(Context ctx, String key, int i) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putInt(key, i).commit();
	}

	public static int getInt(Context ctx, String key,int defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}
	
}
