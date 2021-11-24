package com.example.gitbug.ViewModal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitbug.Repository.BugRepository
import com.example.gitbug.Response.BugResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BugViewModel constructor(private val repository: BugRepository)  : ViewModel() {

    val bugList = MutableLiveData<List<BugResponse>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<BugResponse>> {
            override fun onResponse(call: Call<List<BugResponse>>, response: Response<List<BugResponse>>) {
                bugList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<BugResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}