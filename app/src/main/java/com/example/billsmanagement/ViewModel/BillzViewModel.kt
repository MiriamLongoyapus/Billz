package com.example.billsmanagement.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.billsmanagement.Model.Bill
import com.example.billsmanagement.Repository.BillzRepository
import kotlinx.coroutines.launch

class BillzViewModel: ViewModel(){
    val billsRepo = BillzRepository()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsRepo.saveBill(bill)
        }
    }
}