package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxkj.fit_5a.app.MainApplication;
import com.jxkj.fit_5a.base.HistoryEquipmentData;
import com.jxkj.fit_5a.conpoment.constants.ConstValues;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： litongge
 * 时间： 2018/4/24 20:22
 * 邮箱；ltg263@126.com
 * 描述：SharedPreferences 的工具类
 */
public class SharedHistoryEquipment {
    private static SharedHistoryEquipment sharedUtils;
    public static String SHARED_PREFS_NAME = "SharedHistoryEquipment";

    public static SharedHistoryEquipment singleton() {
        if (sharedUtils == null) {
            sharedUtils = new SharedHistoryEquipment(MainApplication.getContext(), SHARED_PREFS_NAME);
        }
        return sharedUtils;
    }

    private SharedPreferences mSharedPrefs;

    private SharedHistoryEquipment(Context context, String sharedPrefsName) {
        mSharedPrefs = context.getSharedPreferences(sharedPrefsName, Context.MODE_PRIVATE);
    }

    public void putSharedHistoryEquipment(List<HistoryEquipmentData> lists) {

        SharedPreferences.Editor editor = mSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lists);
        editor.putString("String", json);
        editor.commit();
    }

    public List<HistoryEquipmentData> getSharedHistoryEquipment() {
        Gson gson = new Gson();
        String json = mSharedPrefs.getString("String", null);
        Type type = new TypeToken<List<HistoryEquipmentData>>() {
        }.getType();
        List<HistoryEquipmentData> arrayList = gson.fromJson(json, type);
        return arrayList;
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
}
