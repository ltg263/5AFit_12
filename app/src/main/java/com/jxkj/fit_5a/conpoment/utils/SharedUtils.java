package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;

/**
 * 作者： litongge
 * 时间： 2018/4/24 20:22
 * 邮箱；ltg263@126.com
 * 描述：SharedPreferences 的工具类
 */
public class SharedUtils {
	private static SharedUtils sharedUtils;
	public static String SHARED_PREFS_NAME = ConstValues.APPNAME_ENGLISH;

	public static SharedUtils singleton() {
		if (sharedUtils == null) {
			sharedUtils = new SharedUtils(MainApplication.getContext(), SHARED_PREFS_NAME);
		}
		return sharedUtils;
	}
	
	private SharedPreferences mSharedPrefs;

	private SharedUtils(Context context, String sharedPrefsName) {
		mSharedPrefs = context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);
	}

	public <T> void put(String key, T value) {
		put(new String[] { key }, new Object[] { value });
	}

	public <T> void put(String[] keys, T[] values) {
		if (values != null && keys != null) {
			Editor edit = mSharedPrefs.edit();
			for (int i = 0; i < values.length; i++) {
				T value = values[i];
				int index = i;
				if (index >= keys.length) {
					index = keys.length - 1;
				}
				String key = keys[index];
				Class<? extends Object> cls = value.getClass();
				if (cls == Integer.class || cls == int.class) {
					edit.putInt(key, (Integer) value);
				} else if (cls == Boolean.class || cls == boolean.class) {
					edit.putBoolean(key, (Boolean) value);
				} else if (cls == Float.class || cls == float.class) {
					edit.putFloat(key, (Float) value);
				} else if (cls == Long.class || cls == long.class) {
					edit.putLong(key, (Long) value);
				} else if (cls == String.class) {
					edit.putString(key, (String) value);
				}
			}
			edit.commit();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, T defValue) {
		Object result = null;
		if (defValue != null && key != null) {
			Class<? extends Object> cls = defValue.getClass();
			if (cls == Integer.class || cls == int.class) {
				result = mSharedPrefs.getInt(key, (Integer) defValue);
			} else if (cls == Boolean.class || cls == boolean.class) {
				result = mSharedPrefs.getBoolean(key, (Boolean) defValue);
			} else if (cls == Float.class || cls == float.class) {
				result = mSharedPrefs.getFloat(key, (Float) defValue);
			} else if (cls == Long.class || cls == long.class) {
				result = mSharedPrefs.getLong(key, (Long) defValue);
			} else if (cls == String.class) {
				result = mSharedPrefs.getString(key, (String) defValue);
			}
		}
		return (T) result;
	}

	public void clear() {
		Editor edit = mSharedPrefs.edit();
		edit.clear();
		edit.commit();
	}

	public void clear(String key) {
		Editor edit = mSharedPrefs.edit();
		edit.remove(key);
		edit.commit();
	}
	public static String getToken(){
		Log.w("token","--->>:"+singleton().get(ConstValues.TOKEN,""));
//		return singleton().get(ConstValues.TOKEN,"");
		return "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHQiOiJ7XCJyZWxhdGlvblR5cGVcIjowLFwicmVsYXRpb25JZFwiOjAsXCJzeXNVc2VySWRcIjo3OX0iLCJ1c2VySW5mbyI6IntcImFnZVwiOjI3LFwiYXZhdGFyXCI6XCJodHRwczovL3RoaXJkd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL1BpYWp4U3FCUmFFTHhPeFJsSGVqNG9HM2Q3bnZjaWJBQXpYMk1iaWJvczE2TWg0MmlhRlBrbGVpYWljUzNWTDE2TlBNT3dlWUVnM2EzUURtdmJwRm5KdlUzbXFnLzEzMlwiLFwiYmlydGhkYXlcIjo3NjI0NTEyMDAwMDAsXCJjbGllbnRUeXBlXCI6NCxcImNyZWF0ZVRpbWVcIjoxNjA0NTU4MDc4MDAwLFwiZ2VuZGVyXCI6MSxcImhlaWdodFwiOjE2OS42MCxcImlkXCI6NDcsXCJsZXZlbFwiOjAsXCJsZXZlbE5vXCI6XCJcIixcImxvZ2luVHlwZVwiOjEsXCJuaWNrTmFtZVwiOlwi5ZGGLlwiLFwib3BlbklkXCI6XCJvN3hHTTVlaV9wWTIycEpmYW5PZ21PQ2NMOGxvXCIsXCJzaWduXCI6XCLnlJ_mtLvmgLvmmK_orqnmiJHku6zpgY3kvZPps57kvKTvvIzkvYbliLDlkI7mnaXpgqPkupvlj5fkvKTnmoTlnLDmlrnvvIzkuIDlrprkvJrlj5jmiJDmiJHku6zmnIDlvLrlo67nmoTlnLDmlrlcIixcInN0YXR1c1wiOjEsXCJ1aW9uSWRcIjpcIm9kTE5mNm5HLWZod29zbmVNTk93al9mU2ZSeXNcIixcInVzZXJOb1wiOlwiMTUxNjg1MzE5ODhcIixcIndlaWdodFwiOjY4LjQwfSIsInVzZXJfbmFtZSI6IntcInVzZXJuYW1lVHlwZVwiOjMsXCJ1c2VybmFtZVwiOlwib2RMTmY2bkctZmh3b3NuZU1OT3dqX2ZTZlJ5c1wifSIsInNjb3BlIjpbInJlYWQiXSwiZXhwIjoxOTMwMTk4NDU0LCJqdGkiOiJhWTdJNGJQbGpESmotY3lhRTJaWl9GNzktc0UiLCJjbGllbnRfaWQiOiJ0ZXN0X2NsaWVudCJ9.Oap2Cf6jH2cqT8RnqLpqdWLu4iq0DL8sKf6jL9ks4RU";
	}
	public static int getUserId(){
		return singleton().get(ConstValues.USERID,0);
	}

}
