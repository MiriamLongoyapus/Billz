package com.example.billsmanagement.Model

import com.google.gson.annotations.SerializedName

data class register(
    @SerializedName("first_name") var firstName:String,
    @SerializedName("last_name") var lastName:String,
    @SerializedName("user_id") var userId: String,
    @SerializedName("phone_number") var phoneNumber:String,
    val email:String,
    var password:String
)

