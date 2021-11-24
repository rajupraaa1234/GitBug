package com.example.gitbug.ApiService


import com.example.gitbug.Response.BugResponse
import com.example.gitbug.Utility.ApiConstant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServices {
    @GET("issues")
    fun getUser(): Call<List<BugResponse>>


    @GET("issues")
    fun getAllUser() : Call<List<BugResponse>>

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