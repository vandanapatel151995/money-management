package com.example.moneymanagement.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "IncomeEntity")
data class IncomeEntity(

    @ColumnInfo(name = "IncomeType")
    @NotNull var IncomeType: String,

    @ColumnInfo(name = "IncomeDate")
    @NotNull var IncomeDate: String,

    @ColumnInfo(name = "IncomeAmount")
    @NotNull var IncomeAmount: Int
) {
    @PrimaryKey(autoGenerate = true)
    var IncomeId: Int = 0
}

