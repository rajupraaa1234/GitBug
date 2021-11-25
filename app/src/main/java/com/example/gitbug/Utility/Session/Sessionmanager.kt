package com.example.gitbug.Utility.Session


import com.example.gitbug.Utility.AppConstant.SaveIssueList

import android.app.Application
import com.example.gitbug.Response.BugResponse
import com.example.gitbug.Response.CommentResponse
import com.example.gitbug.App.appController

class Sessionmanager private constructor(application: Application) {
    private val sharedPreferences: PreferenceUtil

    fun setIssueList(list: List<BugResponse?>?){
        sharedPreferences.saveIssueList(SaveIssueList, list)
    }
    fun getIssueList() : List<BugResponse>? {
        return sharedPreferences.getIssueList(SaveIssueList)
    }
    fun setCommentList(list: List<CommentResponse?>?, id: Int) {
        sharedPreferences.saveCommentList(id.toString(), list)
    }

    fun getCommentList(id: Int): List<CommentResponse>? {
        return sharedPreferences.getCommentList(id.toString())
    }

    fun clear() {
        sharedPreferences.clear()
    }

    companion object {
        // private SharedPreferences sharedPreferences;
        private var sinstance: Sessionmanager? = null
        private fun init(application: Application) {
            if (sinstance == null) {
                sinstance = Sessionmanager(application)
            }
        }

        fun get(): Sessionmanager? {
            appController.instance?.let { init(it) }
            return sinstance
        }
    }

    init {
        sharedPreferences = PreferenceUtil(application)
    }
}