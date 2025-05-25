package com.example.firstapp.lesson28.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import kotlinx.coroutines.launch

class Lesson28 : AppCompatActivity() {
    private lateinit var btnDownload: Button
    private lateinit var recyclerView: RecyclerView
    private val viewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson28)
        setupView()
        setupClick()

    }

    private fun setupView() {
        btnDownload = findViewById(R.id.btnDownload)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun setupClick() {
        btnDownload.setOnClickListener {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.data.collect { data ->
                        recyclerView.adapter = NetworkAdapter(data) { clickedId ->
                            Toast.makeText(
                                this@Lesson28,
                                "ID арт проекта: $clickedId",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            viewModel.loadFacts()
        }

    }
}


