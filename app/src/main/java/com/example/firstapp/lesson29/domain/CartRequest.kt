package com.example.firstapp.lesson29.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CartRequest(
    val userId: Int,
    val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
    val products: List<CartProduct>
)