package com.example.firstapp.lesson23.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityLesson23Binding
import com.example.firstapp.lesson23.presentation.adapter.RecyclerViewAdapter

class Lesson23 : AppCompatActivity() {
    private var binding: ActivityLesson23Binding? = null
    private val viewModel: CountViewModel by viewModels()
    private var adapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson23Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupView()
    }

    fun setupView() {
        //viewModel = CountViewModel()

        adapter = RecyclerViewAdapter(mutableListOf())
        binding?.recyclerViewList?.adapter = adapter
        binding?.recyclerViewList?.layoutManager = LinearLayoutManager(this)

        //Подписка
        viewModel.count.observe(this) {
                filteredList -> adapter?.update(filteredList)
        }

        binding?.touchButton?.setOnClickListener {
            val filterText = binding?.filter?.text.toString()
            viewModel.touchButton(filterText)

        }
    }
}