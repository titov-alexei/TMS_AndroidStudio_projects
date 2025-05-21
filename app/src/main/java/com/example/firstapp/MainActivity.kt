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
import com.example.firstapp.lesson22.Lesson22
import com.example.firstapp.lesson23.homework23.presentation.Homework23
import com.example.firstapp.lesson24.presentation.Lesson24
import com.example.firstapp.lesson25.presentation.Lesson25
import com.example.firstapp.lesson26.presentation.Lesson26

//import com.example.firstapp.lesson21.Lesson21

class MainActivity : AppCompatActivity() {

    //private lateinit var wifiReceiver: WifiReceiver
    private var btnLesson18: Button? = null
    private var btnLesson19: Button? = null
    private var btnLesson20: Button? = null
    private var btnLesson21: Button? = null
    private var btnLesson22: Button? = null
    private var btnLesson23: Button? = null
    private var btnLesson24: Button? = null
    private var btnLesson25: Button? = null
    private var btnLesson26: Button? = null


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

        btnLesson22?.setOnClickListener {
            val intent = Intent(this, Lesson22::class.java)
            startActivity(intent)
        }

        btnLesson23?.setOnClickListener {
            val intent = Intent(this, Homework23::class.java)
            startActivity(intent)
        }

        btnLesson24?.setOnClickListener {
            val intent = Intent(this, Lesson24::class.java)
            startActivity(intent)
        }

        btnLesson25?.setOnClickListener {
            val intent = Intent(this, Lesson25::class.java)
            startActivity(intent)
        }

        btnLesson26?.setOnClickListener {
            val intent = Intent(this, Lesson26::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
       // wifiReceiver = WifiReceiver()
        btnLesson18 = findViewById(R.id.btnLesson18)
        btnLesson19 = findViewById(R.id.btnLesson19)
        btnLesson20 = findViewById(R.id.btnLesson20)
        btnLesson21 = findViewById(R.id.btnLesson21)
        btnLesson22 = findViewById(R.id.btnLesson22)
        btnLesson23 = findViewById(R.id.btnLesson23)
        btnLesson24 = findViewById(R.id.btnLesson24)
        btnLesson25 = findViewById(R.id.btnLesson25)
        btnLesson26 = findViewById(R.id.btnLesson26)
        //val filter = IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION)
        //registerReceiver(wifiReceiver, filter)
    }

    private fun useContext() {
        val context = this.applicationInfo.toString()
        Log.d("MYLOG", context)
    }
}