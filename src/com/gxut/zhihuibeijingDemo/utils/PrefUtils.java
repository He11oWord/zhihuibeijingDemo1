package com.gxut.zhihuibeijingDemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference�ķ�װ��
 * @author Kevin
 * 
 */
public class PrefUtils {

	public static final String PREF_NAME = "config";

	/**
	 * ��ȡ�����ֵ
	 * @param ������
	 * @param key key
	 * @param defaultValue Ĭ��ֵ
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
	 * @param ctx ������
	 * @param key
	 * @param value ֵ
	 */
	public static void setBoolean(Context ctx, String key, boolean value) {
		SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}
}
