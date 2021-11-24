package com.example.gitbug.CallBack

interface BugListItemCallListner {
    fun onClick(id: Int,title : String,body:String,CommentNumber : Int)
}