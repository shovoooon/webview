package com.bdjobs.today.utill

import android.content.Context
import android.net.ConnectivityManager


object DetectConnection {
    fun checkInternetConnection(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isAvailable
                && cm.activeNetworkInfo!!.isConnected)
    }
}