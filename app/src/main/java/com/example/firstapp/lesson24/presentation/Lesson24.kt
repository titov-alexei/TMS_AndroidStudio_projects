package com.example.firstapp.lesson24.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityLesson24Binding
import com.example.firstapp.lesson24.presentation.adapter.ProductAdapter

class Lesson24 : AppCompatActivity() {
    private lateinit var binding: ActivityLesson24Binding
    private val viewModel: ProductViewModel by viewModels()
    private var adapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson24Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAdapter()
        setupViewModel()
        setupClick()
    }

    private fun setupView() {
        with(binding) {
            btnAddProduct.reusable.text = getString(R.string.add)
            btnClearProductList.reusable.text = getString(R.string.clear_List)
        }
    }

    private fun setupAdapter() {
        with(binding) {
            adapter = ProductAdapter(
                onDeleteClick = {id -> viewModel.deleteProduct(id)},
                onReadyClick = {id, isChecked ->
                    viewModel.checkProduct(id, isChecked)
                }
            )
            recyclerViewProduct.adapter = adapter
            recyclerViewProduct.layoutManager = LinearLayoutManager(this@Lesson24)

        }
    }

    private fun setupViewModel() {
        viewModel.product.observe(this) { products ->
            adapter?.updateItems(products ?: emptyList())
        }
    }

    private fun setupClick() {
        with(binding) {
            btnAddProduct.reusable.setOnClickListener {
                if (enterProduct.text.isNotBlank()) {
                    viewModel.addProduct(enterProduct.text.toString())
                    enterProduct.text.clear()
                } else {
                    Toast.makeText(this@Lesson24, R.string.no_data, Toast.LENGTH_SHORT).show()
                }
            }

            btnClearProductList.reusable.setOnClickListener {
                viewModel.clearProduct()
            }
        }
    }
}