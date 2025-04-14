package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.lesson18.Lesson18
import com.example.firstapp.lesson19.Lesson19
import com.example.firstapp.lesson20.Lesson20
import com.example.firstapp.lesson21.Lesson21

class MainActivity : AppCompatActivity() {

    //private lateinit var wifiReceiver: WifiReceiver
    private var btnLesson18: Button? = null
    private var btnLesson19: Button? = null
    private var btnLesson20: Button? = null
    private var btnLesson21: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MYLOG", "MainActivity onCreate()")
        initView()  //Инициализация компонентов на MainActivity
        useContext() //Для урока 17, использование context

        btnLesson18?.setOnClickListener {
            val intent = Intent(this, Lesson18::class.java)
            startActivity(intent)
        }

        btnLesson19?.setOnClickListener {
            val intent = Intent(this, Lesson19::class.java)
            startActivity(intent)
        }

        btnLesson20?.setOnClickListener {
            val intent = Intent(this, Lesson20::class.java)
            startActivity(intent)
        }

        btnLesson21?.setOnClickListener {
            val intent = Intent(this, Lesson21::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
       // wifiReceiver = WifiReceiver()
        btnLesson18 = findViewById(R.id.btnLesson18)
        btnLesson19 = findViewById(R.id.btnLesson19)
        btnLesson20 = findViewById(R.id.btnLesson20)
        btnLesson21 = findViewById(R.id.btnLesson21)
        //val filter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        //registerReceiver(wifiReceiver, filter)
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
        //unregisterReceiver(wifiReceiver)
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