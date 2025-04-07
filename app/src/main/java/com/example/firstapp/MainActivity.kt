package com.example.firstapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var wifiReceiver: WifiReceiver
    private lateinit var btnLesson18: Button
    private lateinit var btnLesson19: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MYLOG", "MainActivity onCreate()")
        initView()  //Инициализация компонентов на MainActivity
        useContext() //Для урока 17, использование context

        btnLesson18.setOnClickListener {
            val intent = Intent(this, Lesson18::class.java)
            startActivity(intent)
        }

        btnLesson19.setOnClickListener {
            val intent = Intent(this, Lesson19::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        wifiReceiver = WifiReceiver()
        btnLesson18 = findViewById(R.id.btnLesson18)
        btnLesson19 = findViewById(R.id.btnLesson19)
        val filter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, filter)
    }

    private fun useContext() {
        val context = this.applicationInfo.toString()
        Log.d("MYLOG", context)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MYLOG", "MainActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYLOG", "MainActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYLOG", "MainActivity onPause()")
        unregisterReceiver(wifiReceiver)
    }

    override fun onStop() {
        super.onStop()
        Log.d("MYLOG", "MainActivity onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MYLOG", "MainActivity onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MYLOG", "MainActivity onDestroy()")
    }
}