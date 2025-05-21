package com.example.firstapp.lesson26.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.firstapp.lesson26.domain.Product

class SearchDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
}