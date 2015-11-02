package com.gxut.zhihuibeijingDemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference�ķ�װ��
 * 
 * @author Kevin
 * 
 */
public class PrefUtils {

	public static final String PREF_NAME = "config";

	/**
	 * ��ȡ�����ֵ
	 * 
	 * @param ������
	 * @param key
	 *            key
	 * @param defaultValue
	 *            Ĭ��ֵ
	 * @return
	 */
	public static boolean getBoolean(Context ctx, String key,
			boolean defaultValue) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	/**
	 * ���������ֵ
	 * 
	 * @param ctx
	 *            ������
	 * @param key
	 * @param value
	 *            ֵ
	 */
	public static void setBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
	
	/**
	 * ����String���͵�ֵ
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
	 * ����int���͵�ֵ
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
