package com.example.gitbug.Utility.Session

import android.content.Context
import com.example.gitbug.Utility.AppConstant.App_NAME
import android.content.SharedPreferences
import com.example.gitbug.Utility.Session.PreferenceUtil
import com.example.gitbug.Response.BugResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.gitbug.Response.CommentResponse
import com.example.gitbug.Utility.AppConstant

class PreferenceUtil(private val context: Context) {
    private val mSpref: SharedPreferences
    private val TAG = PreferenceUtil::class.java.simpleName
    fun setStringData(key: String?, value: String?) {
        val appInstallInfoEditor = mSpref.edit()
        appInstallInfoEditor.putString(key, value)
        appInstallInfoEditor.apply()
    }

    fun getStringData(key: String?): String? {
        return mSpref.getString(key, "")
    }

    fun saveIssueList(key: String?, list: List<BugResponse?>?) {
        val editor = mSpref.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getIssueList(key: String?): List<BugResponse>? {
        val gson = Gson()
        val json = mSpref.getString(key, null)
        val type = object : TypeToken<List<BugResponse?>?>() {}.type
        if(json==null) return null
        return gson.fromJson(json, type)
    }

    fun saveCommentList(key: String?, list: List<CommentResponse?>?) {
        val editor = mSpref.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getCommentList(key: String?): List<CommentResponse> ?{
        val gson = Gson()
        val json = mSpref.getString(key, null)
        val type = object : TypeToken<List<CommentResponse?>?>() {}.type
        if(json==null) return null
        return gson.fromJson(json, type)
    }

    fun clear() {
        mSpref.edit().clear().apply()
    }

    companion object {
        val SHARED_PREF_NAME = App_NAME
    }

    init {
        mSpref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}