package com.example.firstapp.lesson26.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.databinding.ActivityLesson26Binding
import com.example.firstapp.lesson26.presentation.adapter.SearchAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lesson26 : AppCompatActivity() {
    private lateinit var binding: ActivityLesson26Binding
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter
    private var debounceJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson26Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupObservers()
        setupSearchInput()
    }

    private fun setupAdapter() {
        adapter = SearchAdapter()
        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearch.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.searchResult.collect { users ->
                adapter.submitList(users)
                binding.recyclerViewSearch.visibility = if (users.isNotEmpty()) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBarSearch.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            viewModel.errorMessage.collect { error ->
                binding.statusSearch.text = error
                binding.statusSearch.visibility = if (error != null) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupSearchInput() {
        binding.editTextSearch.doAfterTextChanged { editable ->
            debounceJob?.cancel()
            debounceJob = lifecycleScope.launch {
                delay(300)
                viewModel.setSearchQuery(editable?.toString() ?: "")
            }
        }
    }

    override fun onDestroy() {
        debounceJob?.cancel()
        super.onDestroy()
    }
}

