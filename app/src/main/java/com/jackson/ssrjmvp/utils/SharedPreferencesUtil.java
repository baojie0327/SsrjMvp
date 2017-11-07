package com.jackson.ssrjmvp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
    private static final String SHARE_PREFER = "ssrj"; //SP保存的xml文件名
    public static  final String LOGIN_STATUS="login_sttaus"; //登录状态

    private static Editor editor;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferencesUtil spUtil;

    public SharedPreferencesUtil() {

    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (spUtil == null) {
            spUtil = new SharedPreferencesUtil();
            sharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
        return spUtil;
    }

    public boolean putKey(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public String getKey(String key) {
        return sharedPreferences.getString(key, "");
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public boolean setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean clear() {
        editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }

    public static void setString(Context context, String key, String Value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor editor = mSharedPreferences.edit();
        editor.putString(key, Value);
        editor.commit();
    }

    //从配置文件中获取String类型的数据
    public static String getString(Context context, String key) {
        String value = null;
        SharedPreferences mSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        value = mSharedPreferences.getString(key, "");
        return value;
    }

    //保存Boolean类型的数据到配置类型
    //key表示保存到参数
    //value表示参数值
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences mSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 记录用户别名的SP文件
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void setNewSP(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("jPush", Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 从SP文件中获取用户别名
     * @param context 上下文
     * @param key 键
     * @return 值
     */
    public static String getAlias(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("jPush", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");

    }

}
