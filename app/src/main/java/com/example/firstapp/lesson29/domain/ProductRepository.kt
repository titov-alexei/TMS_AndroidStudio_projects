package com.example.firstapp.lesson29.domain

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun addToCart(productId: Int)
}