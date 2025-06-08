package com.example.firstapp.lesson29.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.firstapp.R
import com.example.firstapp.databinding.FakeProductLayoutBinding
import com.example.firstapp.lesson29.domain.Product

class ProductAdapter(
    private val onItemClick: (Product) -> Unit,
    private val onAddToCart: (Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var items: List<Product> = emptyList()

    inner class ProductViewHolder(private val binding: FakeProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                titleProduct.text = product.title
                descriptionProduct.text = product.description
                priceProduct.text = "$${"%.2f".format(product.price)}"

                // Загрузка изображения с помощью Coil
                imageProduct.load(product.image) {
                    crossfade(true)
                    error(R.drawable.logo)
                }

                root.setOnClickListener { onItemClick(product) }

                btnAddProductInCart.setOnClickListener {
                    onAddToCart(product.id)
                }
            }
        }
    }

    fun submitList(newItems: List<Product>) {
        items = newItems
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = FakeProductLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}