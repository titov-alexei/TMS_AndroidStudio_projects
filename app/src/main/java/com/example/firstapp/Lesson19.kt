package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainer

class Lesson19 : AppCompatActivity() {

    companion object {
        const val HELLO_STRING_KEY = "HELLO_STRING"
    }

    private lateinit var btnNextActivity: Button
    private lateinit var btnTransferToFragmentA: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson19)
        init() //Инициализация
        goNextActivity() //Переход на SecondActivity с передачей строки
        transferDataToFragment() //Передача строки во Fragment А
    }

    private fun init() {
        btnNextActivity = findViewById(R.id.btnNextActivity)
        btnTransferToFragmentA = findViewById(R.id.btnTransferToFragmentA)
        supportFragmentManager.beginTransaction()       //Задача 2. Ставим в контейнер FragmentA
            .add(R.id.fragmentContainerL19, FragmentA())
            .commit()
    }

    private fun goNextActivity() {
        val strHello = "Hello, Second Activity!"
        btnNextActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(HELLO_STRING_KEY, strHello)
            startActivity(intent)
        }
    }

    //Задача №4. Передать строку Data from Activity из Активити во Фрагмент
    private fun transferDataToFragment() {
        btnTransferToFragmentA.setOnClickListener {
            val tempFragment = FragmentA.getInstance("Data from Activity")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerL19, tempFragment)
                .commit()
        }
    }


}