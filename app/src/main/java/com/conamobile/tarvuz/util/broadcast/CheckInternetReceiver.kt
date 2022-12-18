package com.conamobile.tarvuz.util.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.conamobile.tarvuz.util.NetworkHelper

class CheckInternetReceiver(var actionChangeInternet: ((Boolean) -> Unit)? = null) :
    BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (hasInternet(context)) actionChangeInternet?.invoke(true)
        else actionChangeInternet?.invoke(false)
    }

    private fun hasInternet(context: Context): Boolean {
        val networkHelper = NetworkHelper(context)
        return networkHelper.isNetworkConnected()
    }
}