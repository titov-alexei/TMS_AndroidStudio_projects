package com.example.firstapp.lesson19

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityLesson19Binding
import com.example.firstapp.lesson18.SecondActivity

class Lesson19 : AppCompatActivity() {

    companion object {
        const val HELLO_STRING_KEY = "HELLO_STRING"
    }

    private var binding: ActivityLesson19Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson19Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        goNextActivity() //Переход на SecondActivity с передачей строки
        //setupInit()
    }

    /*private fun setupInit() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerL19, HomeFragment())
            .commit()
    }*/

   /* override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_graph).navigateUp()
    }*/

    private fun goNextActivity() {
        val strHello = "Hello, Second Activity!"
        binding?.btnNextActivity?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(HELLO_STRING_KEY, strHello)
            startActivity(intent)
        }
    }

    //Задача №4. Передать строку Data from Activity из Активити во Фрагмент
    /*private fun transferDataToFragment() {
        btnTransferToFragmentA.setOnClickListener {
            val tempFragment = FragmentA.getInstance("Data from Activity")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerL19, tempFragment)
                .commit()
        }
    }*/
}