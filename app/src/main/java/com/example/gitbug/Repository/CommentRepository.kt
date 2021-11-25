package com.example.gitbug.Repository

import com.example.gitbug.ApiService.ApiServices



class CommentRepository constructor(private val retrofitService: ApiServices) {
    fun getAllCommentList(id:Int) = retrofitService.getAllComment(id)
}