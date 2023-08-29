package com.example.billsmanagement.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class BillzDb: RoomDatabase() {
    abstract fun billsDao(): BillsDao

    companion object{
        var database: BillzDb? = null

        fun getDabase(context: Context): BillzDb{
            if (database==null){
                database = Room
                    .databaseBuilder(context, BillzDb::class.java, "BillzDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillzDb
        }
    }
}