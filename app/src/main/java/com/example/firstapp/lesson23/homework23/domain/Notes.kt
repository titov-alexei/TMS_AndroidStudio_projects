package com.example.firstapp.lesson23.homework23.domain

data class Notes (val id: Long = System.currentTimeMillis(),
                  val textNotes: String,
                  val dateNotes: Long = System.currentTimeMillis()
)