package com.example.firstapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    private lateinit var editText: EditText
    private lateinit var wifiReceiver: WifiReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MYLOG", "MainActivity onCreate()")
        initView()  //Инициализация компонентов на MainActivity
        useContext()

        textView.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            val name = editText.text.toString()
            Log.d("MYLOG", name)
            intent.putExtra("key", name)
            startActivity(intent)
        }
    }

    private fun initView() {
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        wifiReceiver = WifiReceiver()
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