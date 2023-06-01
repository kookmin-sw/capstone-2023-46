package com.example.health.network

import com.example.health.network.request.LoginRequest
import com.example.health.network.request.SignUpRequest
import com.example.health.network.response.CalendarResponse
import com.example.health.network.response.LoginResponse
import com.example.health.network.response.RoutineListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/exercise")
    suspend fun getExercise(): String

    @POST("/api/signup")
    suspend fun signUp(@Body data: SignUpRequest): String

    @POST("/api/login")
    suspend fun login(@Body data: LoginRequest): LoginResponse

    @GET("/routine")
    suspend fun getRoutineList(): List<RoutineListResponse>

    @GET("/calendar")
    suspend fun getCalendarList(): List<CalendarResponse>
}