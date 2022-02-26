package com.example.trendyol.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

object NetworkUtil {

    fun isNetworkAvailable(context: Context): Boolean {
        var connected = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // For devices with API >= 23
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            // NET_CAPABILITY_VALIDATED - Indicates that connectivity on this network was successfully validated.
            // NET_CAPABILITY_INTERNET - Indicates that this network should be able to reach the internet.
            networkCapabilities?.let { networkCapabilitiesInfo ->
                if (networkCapabilitiesInfo.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && networkCapabilitiesInfo.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    connected = true
                }
            }
        } else {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            activeNetwork?.let { activeNetworkInfo -> if (activeNetworkInfo.isConnected) connected = true }
        }
        return connected
    }
}