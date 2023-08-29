package com.example.billsmanagement.Repository

import com.example.billsmanagement.API.APIClient
import com.example.billsmanagement.API.ApiInterface
import com.example.billsmanagement.Model.RegisterRequest
import com.example.billsmanagement.Model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RegisterRepository {
    var client= APIClient.buildClient(ApiInterface::class.java)
    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return withContext(Dispatchers.IO){
            client.registerUser(registerRequest)
        }
    }


}



