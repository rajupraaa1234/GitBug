package com.example.gitbug.NetworkHandler

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Network {
    fun isNetworkConnected(context : Context) : Boolean{
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null

        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                   return true
                }
            }
            else{
                return false
            }
        }
        return false
    }
}