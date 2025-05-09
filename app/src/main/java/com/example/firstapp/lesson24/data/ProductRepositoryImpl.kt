package com.example.firstapp.lesson24.data

import com.example.firstapp.lesson24.domain.ProductRepository
import com.example.firstapp.lesson24.domain.Products

class ProductRepositoryImpl : ProductRepository {
    private val products = mutableListOf<Products>()

    override suspend fun getProduct(): List<Products> = products

    override suspend fun addProduct(product: Products) {
        products.add(product)
    }

    override suspend fun deleteProduct(id: Long) {
        products.removeAll { it.id == id }
    }

    override suspend fun clearAll() {
        products.clear()
    }
}
