package com.example.firstapp.lesson18

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.lesson19.Lesson19
import com.example.firstapp.R

class Lesson18 : AppCompatActivity() {

    private lateinit var firstButton: Button
    private lateinit var tvTextForEditText: TextView
    private lateinit var btnTextForEditText: Button
    private lateinit var textView : TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson18)
        init()

        textView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val name = editText.text.toString()
            Log.d("MYLOG", name)
            intent.putExtra(Lesson19.HELLO_STRING_KEY, name)
            startActivity(intent)
        }

        //Задача №9. Создать кнопку с обработчиком нажатия для вывода сообщения в Toast
        firstButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.button_pressed), Toast.LENGTH_LONG).show()
        }

        //Задача №10. Поместить текст из EditText в TextView по нажатию кнопки
        btnTextForEditText.setOnClickListener {
            val tempText = editText.text.toString()
            if (tempText.isBlank()) {
                tvTextForEditText.text = getString(R.string.no_data)
            } else {
                tvTextForEditText.text = tempText
            }
        }

    }

    private fun init() {
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        firstButton = findViewById(R.id.firstButton)
        tvTextForEditText = findViewById(R.id.tvTextForEditText)
        btnTextForEditText = findViewById(R.id.btnTextForEditText)
    }
}