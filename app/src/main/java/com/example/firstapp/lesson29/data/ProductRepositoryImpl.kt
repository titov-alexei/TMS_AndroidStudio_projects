package com.example.firstapp.lesson29.data


import com.example.firstapp.lesson29.domain.CartProduct
import com.example.firstapp.lesson29.domain.CartRequest
import com.example.firstapp.lesson29.domain.CartResponse
import com.example.firstapp.lesson29.domain.Product
import com.example.firstapp.lesson29.domain.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ProductApiService
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }

    override suspend fun addToCart(productId: Int) {
        apiService.addToCart(
            CartRequest(
                userId = 0,
                products = listOf(CartProduct(productId, 1))
            ))
    }
}
