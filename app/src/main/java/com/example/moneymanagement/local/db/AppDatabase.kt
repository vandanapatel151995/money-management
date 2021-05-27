package com.example.moneymanagement.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        ExpenseEntity::class,
        IncomeEntity::class,
    CircleEntity::class,
    PaymentsEntity::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDAO(): ExpenseDAO
    abstract fun incomeDAO(): IncomeDAO
    abstract fun circleDAO(): CicleDAO
    abstract fun paymentsDAO():PaymentsDAO
}