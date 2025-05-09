package com.example.firstapp.lesson24.domain

interface ProductRepository {
    suspend fun getProduct(): List<Products>
    suspend fun addProduct(product: Products)
    suspend fun deleteProduct(id: Long)
    suspend fun clearAll()
}