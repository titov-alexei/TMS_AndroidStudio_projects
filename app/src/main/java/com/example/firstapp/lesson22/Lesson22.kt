package com.example.firstapp.lesson22

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityLesson22Binding
import com.example.firstapp.lesson22.personAdapter.Person
import com.example.firstapp.lesson22.personAdapter.RecyclerViewAdapter
import kotlin.random.Random
import kotlin.random.nextInt

class Lesson22 : AppCompatActivity() {
    private var binding: ActivityLesson22Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson22Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        val list = listOf<Person>(
            Person(name = "name1", 0),
            Person(name = "name2", 1),
            Person(name = "name1", 2)
        )

        var copyList = list.toMutableList()

        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        val adapter = RecyclerViewAdapter(list.toMutableList())
        binding?.recyclerView?.adapter = adapter

        binding?.goHomework22?.setOnClickListener {
            val intent = Intent(this, Homework22::class.java)
            startActivity(intent)
        }

        binding?.addSymbolForName?.setOnClickListener {
            copyList.replaceAll {
                val itName = it.name + Random.nextInt(1..10).toString()
                it.copy(itName)
            }

            adapter.update(copyList)

        }

        binding?.btnStorno?.setOnClickListener {
            copyList = list.toMutableList()
            adapter.update(list)
        }
    }
}