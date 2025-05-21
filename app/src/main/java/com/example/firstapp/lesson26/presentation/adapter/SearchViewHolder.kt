package com.example.firstapp.lesson26.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.SearchItemBinding
import com.example.firstapp.lesson26.domain.Product

class SearchViewHolder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(product: Product) {
        binding.itemText.text = product.productName
    }
}