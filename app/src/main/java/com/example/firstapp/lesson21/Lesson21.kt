package com.example.firstapp.lesson21

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.ViewSwitcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityLesson20Binding
import com.example.firstapp.databinding.ActivityLesson21Binding

class Lesson21 : AppCompatActivity() {

    private var binding: ActivityLesson21Binding? = null
    private val list = mutableListOf<String>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson21Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        //createListView() //Урок 21 задача 1: Отобразить список строк с помощью ListView
        //titleSubTitleList() //Урок 21 задача 2: Отображать заголовок и подзаголовок в каждой строке
        clickInRecyclerView() //Урок 21 задача 3: При нажатии на элемент RecyclerView показать Toast
        addTextInRecyclerView() //Урок 21, ДЗ 1. Динаммическое добавление элемента в список
        clearList() //Очистка списка
        checkIsEmpty() //Урок 21, ДЗ 2. Проверка пустой ли лист
    }

    private fun createListView() {
        /*Задача 1: Отобразить список строк с помощью ListView
        Отобразить список из 100 строк ("Элемент 1", "Элемент 2", ...) в ListView.

        val list = ArrayList<String>().apply {
            repeat(100) {
                add("Stroka $it")
            }
        }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        binding?.listView?.adapter = adapter
        */
    }

    private fun titleSubTitleList() {
        /*
        Задача №2
        Использовать simple_list_item_2 в ListView
        Отображать заголовок и подзаголовок в каждой строке(использовать SimpleAdapter)
        */
        /*
        val data: List<Map<String, String>> = List(100) {
            mapOf("title" to "Title $it", "subtitle" to "Sub-title $it")
        }
        val adapter = SimpleAdapter(
            this, data, android.R.layout.simple_list_item_2,
            arrayOf("title", "subtitle"), intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding?.listView?.adapter = adapter
        */
    }

    private fun clickInRecyclerView() {
        /*
        Обработка клика по элементу RecyclerView
        При нажатии на элемент — показать Toast.
         */
        adapter = RecyclerViewAdapter(list)
        binding?.recyclerView?.adapter = adapter
    }

    private fun addTextInRecyclerView() {
        binding?.btnAdd?.setOnClickListener {
            adapter.addItem("Элемент ${list.size + 1}")
            checkIsEmpty()
        }
    }

    private fun clearList() {
        binding?.clearList?.setOnClickListener {
            adapter.clearItem()
            checkIsEmpty()
        }
    }

    private fun checkIsEmpty() {
        if (list.isEmpty()) {
            binding?.textListIsEmpty?.visibility = View.VISIBLE
        } else {
            binding?.textListIsEmpty?.visibility = View.GONE
        }
    }

}