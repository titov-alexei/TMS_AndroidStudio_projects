package com.example.firstapp.lesson23.domain

data class PersonEntity(
    var name: String,
    val id: Int,
    var rating: Double = 5.0
)
