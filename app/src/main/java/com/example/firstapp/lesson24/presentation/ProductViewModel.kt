package com.example.firstapp.lesson24.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.lesson24.data.ProductRepositoryImpl
import com.example.firstapp.lesson24.domain.Products
import com.example.firstapp.lesson24.domain.usecase.AddProductUseCase
import com.example.firstapp.lesson24.domain.usecase.DeleteProductUseCase
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val repository = ProductRepositoryImpl()
    private val addProductUseCase = AddProductUseCase(repository)
    private val deleteProductUseCase = DeleteProductUseCase(repository)
    private val _product = MutableLiveData<List<Products>>(emptyList())
    val product: LiveData<List<Products>?> = _product
    private var checkedStates = mutableMapOf<Long, Boolean>()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val products = addProductUseCase.repository.getProduct()
            products.forEach { product ->
                product.isChecked = checkedStates[product.id] ?: false
            }
            _product.value = products
        }
    }

    fun addProduct(text: String) {
        viewModelScope.launch {
            addProductUseCase(text)
            loadProducts()
        }
    }

    fun deleteProduct(productId: Long) {
        viewModelScope.launch {
            deleteProductUseCase(productId)
            checkedStates.remove(productId)
            loadProducts()
        }
    }

    fun checkProduct(productId: Long, isChecked: Boolean) {
        _product.value = _product.value?.map {
            if (it.id == productId) it.copy(isChecked = isChecked).also {
                checkedStates[productId] = isChecked
            } else it
        }
    }

    fun clearProduct() {
        viewModelScope.launch {
            _product.value = emptyList()
            deleteProductUseCase.clearAll()
        }
    }
}