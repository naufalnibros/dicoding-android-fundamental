package com.naufalnibros.submission_fundamental.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtils {

    @SuppressLint("MissingPermission")
    @JvmStatic
    fun hasInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        } else {
            val nc =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (nc == null) {
                false
            } else {
                nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        }
    }
}