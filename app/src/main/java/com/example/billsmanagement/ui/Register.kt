package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.billsmanagement.Model.RegisterRequest
import com.example.billsmanagement.ViewModel.UserViewModel
import com.example.billsmanagement.databinding.ActivityRegister2Binding
import com.example.billsmanagement.utils.Constants

class Register : AppCompatActivity() {


    val userViewModel: UserViewModel by viewModels()
    lateinit var binding: ActivityRegister2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        redirectUser()
    }
    override fun onResume() {
        super.onResume()
        binding.btnLog.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        binding.btnProceed.setOnClickListener{
            clearErrors()
            validateSignUp()
        }
        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this,regResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, Login::class.java))
            binding.progressBar.visibility= View.GONE
        })
        userViewModel.regLiveData.observe(this, Observer { err ->
            Toast.makeText(this,err.message, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility= View.GONE
        })
        binding.btnLog.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    fun validateSignUp() {
        val firstName=binding.etFirstName.text.toString()
        val lastName=binding.etLastName.text.toString()
        val phoneNumber=binding.etPhone.text.toString()
        val email = binding.etPassword.text.toString()
        val password = binding.etPassword.text.toString()
        val confirm=binding.etConf.text.toString()
        var error = false

        if (firstName.isBlank()) {
            error = true
            binding.tillFirstName.error = "First name is required"
        }
        if (lastName.isBlank()) {
            binding.tillLastName.error = "First name is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tillPassword.error = "Email is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tillPassword.error = "Password is required"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tillPhone.error = "Your phone number is required"
            error = true
        }
        if(!error){
            val registerRequest= RegisterRequest(
                firstName =firstName,
                lastName = lastName,
                email=email,
                phonenumber = phoneNumber,
                password = password,
            )
            userViewModel.registerUser(registerRequest)
            Toast.makeText(this,"Registration of $firstName" +
                    " was sucessful",
                Toast.LENGTH_LONG).show()
        }
    }

    fun clearErrors() {
        binding.tillFirstName.error = null
        binding.tillLastName
        binding.tillPassword.error = null
        binding.tillPassword.error = null
        binding.tillPhone.error = null
        binding.tillConf.error=null
    }
    fun redirectUser(){
        val prefs=getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userId=prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)!!
        if (userId.isBlank()){
            startActivity(Intent(this, Login::class.java))
        }else{
            startActivity(Intent(this,HomeActivity::class.java))
        }
        finish()
    }
}