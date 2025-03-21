package com.example.firstapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log

class WifiReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == WifiManager.WIFI_STATE_CHANGED_ACTION) {
            val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
            when (wifiState) {
                WifiManager.WIFI_STATE_ENABLED -> Log.d("MYLOG", "Wi-fi is enabled")
                WifiManager.WIFI_STATE_DISABLED -> Log.d("MYLOG", "Wi-fi is disabled")
            }
        }


    }
}