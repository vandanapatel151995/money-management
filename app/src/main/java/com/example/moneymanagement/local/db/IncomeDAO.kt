package com.example.moneymanagement.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IncomeDAO {
    @Insert
    fun saveIncome(income: IncomeEntity)

    @Query(value = "Select * from IncomeEntity")
    fun getAllIncome() : List<IncomeEntity>

    @Query(value = "Select SUM(IncomeAmount) from IncomeEntity")
    fun getTotalIncomeAmount(): LiveData<Int>
}
