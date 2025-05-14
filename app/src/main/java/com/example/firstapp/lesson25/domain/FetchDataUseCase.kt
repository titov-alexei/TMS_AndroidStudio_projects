package com.example.firstapp.lesson25.domain

import com.example.firstapp.lesson25.data.NetworkRepository

class FetchDataUseCase(private val repository: NetworkRepository) {
    suspend operator fun invoke(): LoadState {
        return repository.fetchData()
    }
}