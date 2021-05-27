package com.example.moneymanagement.local.db

import androidx.annotation.IntegerRes
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDAO {
    @Insert
    fun saveExpense(transaction: ExpenseEntity)

    @Query(value = "Select * from ExpenseEntity")
    fun getAllExpenses(): LiveData<List<ExpenseEntity>>

    @Query(value = "Select SUM(TransAmount) from ExpenseEntity")
    fun getTotalExpensesAmount(): LiveData<Int>
}
