package com.example.billsmanagement.API

import com.example.billsmanagement.Model.LoginRequest
import com.example.billsmanagement.Model.LoginResponse
import com.example.billsmanagement.Model.RegisterRequest
import com.example.billsmanagement.Model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {
        @POST("/users/register")
        suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

        @POST("users/login")
        suspend fun loginUser(@Body loginRequest: LoginRequest):Response<LoginResponse>
}