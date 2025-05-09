package com.example.firstapp.lesson24.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.firstapp.lesson24.domain.Products

class ProductDiffUtilCallback(
    private val oldListProducts: List<Products>,
    private val newListProducts: List<Products>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldListProducts.size

    override fun getNewListSize(): Int = newListProducts.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListProducts[oldItemPosition].id == newListProducts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListProducts[oldItemPosition] == newListProducts[newItemPosition]
    }
}