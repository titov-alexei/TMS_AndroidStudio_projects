package com.example.firstapp.lesson29.data

import com.example.firstapp.lesson29.domain.CartRequest
import com.example.firstapp.lesson29.domain.CartResponse
import com.example.firstapp.lesson29.domain.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @POST("carts")
    suspend fun addToCart(@Body request: CartRequest): CartResponse

    /*@DELETE("carts/{id}")
    suspend fun deleteCart(@Path("id") id: Int)

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Int)*/
}

