package com.example.billsmanagement.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billsmanagement.Model.RegisterRequest
import com.example.billsmanagement.Model.RegisterResponse
import com.example.billsmanagement.Repository.RegisterRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
        val regLiveData= MutableLiveData<RegisterResponse>()
        val errorLiveData= MutableLiveData<String>()
        val userRepository= RegisterRepository()
        fun registerUser(registerRequest: RegisterRequest){
            viewModelScope.launch {
                val response= userRepository.registerUser(registerRequest)
                if (response.isSuccessful){
                    regLiveData.postValue(response.body())
                }
                else{
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }

    }
