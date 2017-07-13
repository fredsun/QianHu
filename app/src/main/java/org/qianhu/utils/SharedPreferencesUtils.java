package org.qianhu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.qianhu.APP;

public class SharedPreferencesUtils {

    public static String SP_NAME = "config";
    private static SharedPreferences sp;

    public static final String SP_NAME1 = "config1";
    private static SharedPreferences sp1;

	/**
	 * 保存位置
	 */
	public static void setMyLocation(Context context, String lat, String lng, String province, String city, String district, String address) {
		SharedPreferencesUtils.saveString(context, "address", address);
		SharedPreferencesUtils.saveString(context, "longitudeCurrentPosition", lng);
		SharedPreferencesUtils.saveString(context, "latitudeCurrentPosition", lat);
		SharedPreferencesUtils.saveString(context, "province", province);
		SharedPreferencesUtils.saveString(context, "city", city);
		SharedPreferencesUtils.saveString(context, "district", district);
	}
	/**
     * 删除sp
     */
    public static void delSharedPreferences() {
	if (sp == null)
	    sp = APP.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	sp.edit().clear();

	if (sp1 == null)
	    sp1 = APP.getInstance().getSharedPreferences(SP_NAME1, 0);
	sp1.edit().clear();
    }

    /**
     * 保存Boolean 类型的数据
     * 
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
	if (sp1 == null)
	    sp1 = context.getSharedPreferences(SP_NAME1, 0);
	sp1.edit().putBoolean(key, value).commit();
    }

    /**
     * 得到Boolean 类型的数据
     * 
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
	if (sp1 == null)
	    sp1 = context.getSharedPreferences(SP_NAME1, 0);
	return sp1.getBoolean(key, defValue);
    }

    public static void saveString(Context context, String key, String value) {
	if (sp == null)
	    sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
	if (sp == null)
	    sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	return sp.getString(key, defValue);
    }

    public static void saveInteger(Context context, String key, int value) {
	if (sp == null)
	    sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	sp.edit().putInt(key, value).commit();
    }

    public static Integer getInteger(Context context, String key, int defValue) {
	if (sp == null)
	    sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	return sp.getInt(key, defValue);
    }

    /**
     * 删除
     * 
     * @param context
     * @param key
     */
    public static void delSpData(Context context, String key) {
	if (sp == null)
	    sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	Editor editor = sp.edit();
	editor.remove(key);
	// editor.clear();
	editor.commit();
    }

    //读取sp中数据
    public static boolean getBoolean(String key, boolean defValue) {
	if (sp1 == null)
	    sp1 = APP.getInstance().getSharedPreferences(SP_NAME1, 0);
	return sp1.getBoolean(key, defValue);
    }
    
    public static String getString( String key, String defValue) {
	if (sp == null)
	    sp = APP.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	return sp.getString(key, defValue);
    }
    
    public static Integer getInteger(String key, int defValue) {
	if (sp == null)
	    sp = APP.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	return sp.getInt(key, defValue);
    }
    
}
