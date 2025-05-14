package com.example.firstapp.lesson25.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.lesson25.data.NetworkRepository
import com.example.firstapp.lesson25.domain.FetchDataUseCase
import com.example.firstapp.lesson25.domain.LoadState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private val repository = NetworkRepository()
    private val fetchDataUseCase = FetchDataUseCase(repository)

    private val _loadingState = MutableLiveData<LoadState>()
    val loadingState: LiveData<LoadState> = _loadingState

    private var fetchJob: Job? = null

    fun fetchData() {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            _loadingState.value = LoadState.Loading
            try {
                _loadingState.value = fetchDataUseCase().also {
                    require(true)
                }
            } catch (e: CancellationException) {
                Log.d("MYLOG", "--Cancel Job--")
            } catch (e: Exception) {
                _loadingState.value = LoadState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun cancelRequest() {
        fetchJob?.cancel()
        fetchJob = null
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }
}