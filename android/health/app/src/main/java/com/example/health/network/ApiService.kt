package com.example.health.network

import retrofit2.http.GET

interface ApiService {

    @GET("/exercise")
    fun getExercise(): String
}