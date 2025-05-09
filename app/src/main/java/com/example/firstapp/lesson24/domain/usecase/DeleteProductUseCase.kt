package com.example.firstapp.lesson24.domain.usecase

import com.example.firstapp.lesson24.domain.ProductRepository

class DeleteProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Long) {
        repository.deleteProduct(productId)
    }

    suspend fun clearAll() = repository.clearAll()
}