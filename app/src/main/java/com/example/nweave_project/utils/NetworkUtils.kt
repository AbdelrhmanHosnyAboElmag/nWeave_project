package com.example.nweave_project.utils

import android.content.Context
import android.net.ConnectivityManager


class NetworkUtils(context: Context) {
    private val connectivityManager: ConnectivityManager

    init {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    val isOnline: Boolean
        get() {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
}