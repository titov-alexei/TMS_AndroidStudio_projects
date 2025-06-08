package com.example.firstapp.lesson29.domain

data class Product(
    val id : Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)
