package com.example.firstapp.lesson24.domain.usecase

import com.example.firstapp.lesson24.domain.ProductRepository
import com.example.firstapp.lesson24.domain.Products

class AddProductUseCase(val repository: ProductRepository) {
    suspend operator fun invoke(text: String) {
        val product = Products(
            id = System.currentTimeMillis(),
            productName = text,
            isChecked = false
        )
        repository.addProduct(product)
    }
}