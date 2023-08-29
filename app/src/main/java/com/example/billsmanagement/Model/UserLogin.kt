package com.example.billsmanagement.Model

import com.google.gson.annotations.SerializedName

data class UserLogin(
     @SerializedName("phone_number")var user_Id: String,
    val email:String,
    var password:String,
)
