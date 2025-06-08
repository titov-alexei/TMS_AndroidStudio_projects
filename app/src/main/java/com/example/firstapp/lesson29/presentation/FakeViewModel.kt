package com.example.firstapp.lesson29.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.lesson29.domain.Product
import com.example.firstapp.lesson29.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FakeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _cartCount = MutableStateFlow(0)
    val cartCount: StateFlow<Int> = _cartCount

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = repository.getProducts()
        }
    }

    init {
        loadProducts()
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.addToCart(productId)
                // Увеличиваем счетчик только после успешного ответа
                _cartCount.value += 1
                Log.d("CartVM", "Product $productId added. New count: ${_cartCount.value}")
            } catch (e: Exception) {
                Log.e("CartVM", "Add to cart error", e)
                // Можно показать ошибку пользователю
            }
        }
    }
}