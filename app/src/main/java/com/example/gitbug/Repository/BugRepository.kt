package com.example.gitbug.Repository

import com.example.gitbug.ApiService.ApiServices

class BugRepository constructor(private val retrofitService: ApiServices) {
    fun getAllIssueList() = retrofitService.getAllIssuesList()
}

