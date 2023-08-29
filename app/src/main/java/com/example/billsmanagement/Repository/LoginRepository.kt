package com.example.billsmanagement.Repository

import com.example.billsmanagement.API.APIClient
import com.example.billsmanagement.API.ApiInterface
import com.example.billsmanagement.Model.LoginRequest
import com.example.billsmanagement.Model.LoginResponse
import com.example.billsmanagement.Model.RegisterRequest
import com.example.billsmanagement.Model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {

    var client=APIClient.buildClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            client.loginUser(loginRequest)
        }

    }
}