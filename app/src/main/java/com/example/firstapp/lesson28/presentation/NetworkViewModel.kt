package com.example.firstapp.lesson28.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.lesson28.data.ApiFetch
import com.example.firstapp.lesson28.domain.ArtFact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NetworkViewModel: ViewModel() {
    private val apiFetch = ApiFetch()

    private val _data = MutableStateFlow<List<ArtFact>>(emptyList())
    val data: StateFlow<List<ArtFact>> = _data

    fun loadFacts() {
        viewModelScope.launch {
            _data.value = apiFetch.fetchFacts()
        }
    }
}