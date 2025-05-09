package com.example.firstapp.lesson24.domain

data class Products(
    val id: Long = System.currentTimeMillis(),
    val productName: String,
    var isChecked: Boolean,
)
