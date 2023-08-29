package com.example.billsmanagement.Repository

import com.example.billsmanagement.BillzApp
import com.example.billsmanagement.Model.Bill
import com.example.billsmanagement.database.BillzDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillzRepository {
    val db = BillzDb.getDabase(BillzApp.appContext)
    val billsDao = db.billsDao()

    suspend fun saveBill(bill: Bill){
        withContext(Dispatchers.IO){
            billsDao.insertBill(bill)
        }
    }
}