package com.example.firstapp.lesson25.domain

sealed class LoadState {
    object Initial : LoadState()
    object Loading : LoadState()
    data class Success(val data: String) : LoadState()
    data class Error(val exception: String) : LoadState()
}