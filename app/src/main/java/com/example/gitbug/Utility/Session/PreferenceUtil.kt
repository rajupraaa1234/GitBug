package com.example.gitbug.Utility.Session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.gitbug.Response.BugResponse;
import com.example.gitbug.Response.CommentResponse;
import com.example.gitbug.Utility.AppConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class PreferenceUtil {

    public static final String SHARED_PREF_NAME = AppConstant.INSTANCE.getApp_NAME();
    private final SharedPreferences mSpref;
    private final Context context;
    private String TAG = PreferenceUtil.class.getSimpleName();

    public PreferenceUtil(Context context) {
        this.context = context;
        mSpref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setStringData(String key, String value) {
        SharedPreferences.Editor appInstallInfoEditor = mSpref.edit();
        appInstallInfoEditor.putString(key, value);
        appInstallInfoEditor.apply();
    }

    public String getStringData(String key) {
        return mSpref.getString(key, "");
    }

    public void saveIssueList(String key, List<BugResponse> list) {
        SharedPreferences.Editor editor = mSpref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public List<BugResponse> getIssueList(String key) {
        Gson gson = new Gson();
        String json = mSpref.getString(key, null);
        Type type = new TypeToken<List<BugResponse>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void saveCommentList(String key, List<CommentResponse> list) {
        SharedPreferences.Editor editor = mSpref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public List<CommentResponse> getCommentList(String key) {
        Gson gson = new Gson();
        String json = mSpref.getString(key, null);
        Type type = new TypeToken<List<CommentResponse>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void clear() {
        mSpref.edit().clear().apply();
    }

}
