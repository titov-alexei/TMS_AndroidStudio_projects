package com.example.firstapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("MYLOG", "SecondActivity onCreate()")
        textView = findViewById(R.id.secondTextView)
        changeText()
    }

    private fun changeText() {
        val name = intent.getStringExtra("key").toString()
        Log.d("MYLOG", name)
        if (name.isBlank()) {
            textView.text = getString(R.string.no_data)
        } else {
            //Задание 6. Применение форматированного ресурса
            findViewById<TextView>(R.id.secondTextView).apply {
                text = getString(R.string.hello_name_format, name)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("MYLOG", "SecondActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MYLOG", "SecondActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MYLOG", "SecondActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MYLOG", "SecondActivity onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MYLOG", "SecondActivity onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MYLOG", "SecondActivity onDestroy()")
    }
}