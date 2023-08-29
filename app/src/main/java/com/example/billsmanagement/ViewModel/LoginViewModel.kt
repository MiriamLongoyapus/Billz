package com.example.billsmanagement.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billsmanagement.Model.LoginRequest
import com.example.billsmanagement.Model.LoginResponse
import com.example.billsmanagement.Repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val logLiveData= MutableLiveData<LoginResponse>()
    val errorLiveData= MutableLiveData<String>()
    val loginRepository= LoginRepository()
    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch{
            val response= loginRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                logLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}