package com.example.firstapp.lesson24.presentation.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ProductLayoutBinding
import com.example.firstapp.lesson24.domain.Products

class ProductAdapter(
    private val onDeleteClick: (Long) -> Unit,
    private val onReadyClick: (Long, Boolean) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    private var items = emptyList<Products>()

    inner class ItemViewHolder(val binding: ProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(product: Products) {
            with(binding) {
                productName.text = product.productName
                productReady.isChecked = product.isChecked
                deleteProduct.setOnClickListener {
                    onDeleteClick(product.id)
                }

                productReady.setOnCheckedChangeListener(null)
                productReady.isChecked = product.isChecked
                productReady.setOnCheckedChangeListener { _, isChecked ->
                    onReadyClick(product.id, isChecked)
                    updateProductText(binding.productName, isChecked)
                }

                updateProductText(productName, product.isChecked)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ProductLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = items[position]
        holder.onBind(product)
    }

    private fun updateProductText(textView: TextView, isChecked: Boolean) {
        if (isChecked) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun updateItems(newListProducts: List<Products>) {
        val diffUtilResult = DiffUtil.calculateDiff(ProductDiffUtilCallback(items, newListProducts))
        items = newListProducts.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }
}