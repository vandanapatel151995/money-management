package com.example.moneymanagement.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PaymentsDAO {
    @Insert
    fun savePaymentType(income: PaymentsEntity)

    @Query(value = "Select * from PaymentsEntity")
    fun getAllPaymentsType() : LiveData<List<PaymentsEntity>>


}
