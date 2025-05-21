package com.example.firstapp.lesson26.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.lesson26.domain.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val allProducts = listOf(
        Product(1, "Молоко"),
        Product(2, "Апельсин"),
        Product(3, "Яблоко"),
        Product(4, "Капуста"),
        Product(5, "Банан"),
        Product(6, "Вода"),
        Product(7, "Каша"),
    )

    private val _searchResult = MutableStateFlow<List<Product>>(emptyList())
    val searchResult: StateFlow<List<Product>> = _searchResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _searchQuery = MutableStateFlow("")

    private var searchJob: Job? = null

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        performSearch()
    }

    private fun performSearch() {
        searchJob?.cancel()

        val query = _searchQuery.value.trim()
        if (query.isEmpty()) {
            _searchResult.value = emptyList()
            _errorMessage.value = null
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        searchJob = viewModelScope.launch {
            try {
                if(query == "error") throw Exception("Искусственная ошибка")

                delay(300)

                val results = allProducts.filter { product ->
                    product.productName.contains(query, ignoreCase = true)
                }

                _searchResult.value = results
                if(results.isEmpty()) {
                    _errorMessage.value = "Ничего не найдено"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Ошибка: ${e.message.toString()}"
                _searchResult.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}