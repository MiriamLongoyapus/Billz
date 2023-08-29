package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.billsmanagement.Model.LoginRequest
import com.example.billsmanagement.Model.LoginResponse
import com.example.billsmanagement.ViewModel.LoginViewModel
import com.example.billsmanagement.databinding.ActivityLoginBinding
import com.example.billsmanagement.utils.Constants


class Login : AppCompatActivity() {
    val loginViewModel:LoginViewModel by viewModels()

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener{
            clearErrors()
            validateLogin()
        }
        loginViewModel.logLiveData.observe(this, Observer { logResponse ->
            Toast.makeText(this,logResponse.message, Toast.LENGTH_LONG).show()
            persistLogin(logResponse)
            startActivity(Intent(this, HomeActivity::class.java))
        })
        loginViewModel.logLiveData.observe(this, Observer { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeActivity::class.java))
        })
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun validateLogin() {
        val email=binding.etUser.text.toString()
        val password = binding.etPass.text.toString()

        var error = false

        if (email.isBlank()) {
            error = true
            binding.tillUser.error = "First name is required"
        }
        if (password.isBlank()) {
            binding.tillPass.error = "Password is required"
            error = true
        }
        if(!error){
            val loginRequest= LoginRequest(
                email=email,
                password = password,
            )
            loginViewModel.loginUser(loginRequest)
            Toast.makeText(this,"login was successful",Toast.LENGTH_LONG).show()
        }
    }

    fun clearErrors() {
        binding.tillUser.error = null
        binding.tillPass.error = null
    }
    fun persistLogin(LoginResponse: LoginResponse){
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()
        editor.putString(Constants.USER_ID, LoginResponse.userId)
        editor.putString(Constants.ACCESS_TOKEN, LoginResponse.accessToken)
        editor.apply()


    }
}
