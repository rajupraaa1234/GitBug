package com.example.gitbug.ApiService


import com.example.gitbug.Response.BugResponse
import com.example.gitbug.Response.CommentResponse
import com.example.gitbug.Utility.ApiConstant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {


    @GET("issues")
    fun getAllIssuesList() : Call<List<BugResponse>>

    @GET("issues/{id}/comments")
    fun getAllComment(@Path(value = "id", encoded = false) key: Int) : Call<List<CommentResponse>>

    companion object {

        var retrofitService: ApiServices? = null

        fun getInstance() : ApiServices {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstant.Baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiServices::class.java)
            }
            return retrofitService!!
        }
    }
}