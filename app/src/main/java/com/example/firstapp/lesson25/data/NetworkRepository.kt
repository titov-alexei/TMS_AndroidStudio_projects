package com.example.firstapp.lesson25.data

import com.example.firstapp.lesson25.domain.LoadState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlin.random.Random

class NetworkRepository {

    suspend fun fetchData(): LoadState {
        return try {
            delay(3000)
            val rand = Random.nextInt(1,50)
            if(rand <= 20) {
                throw Exception("ID $rand")
            }

            LoadState.Success("Data loaded successfully $rand")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            LoadState.Error("Error downloading. ${e.message}")
        }
    }
}