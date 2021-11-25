package com.example.gitbug.ViewModal.CommentViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitbug.Repository.CommentRepository
import com.example.gitbug.Response.CommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentViewModel constructor(private val repository: CommentRepository)  : ViewModel() {

    val commentList = MutableLiveData<List<CommentResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCommentList(id:Int) {
        val response = repository.getAllCommentList(id)
        response.enqueue(object : Callback<List<CommentResponse>> {
            override fun onResponse(call: Call<List<CommentResponse>>, response: Response<List<CommentResponse>>) {
                commentList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CommentResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}