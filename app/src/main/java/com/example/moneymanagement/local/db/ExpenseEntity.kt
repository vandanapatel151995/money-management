package com.example.moneymanagement.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "ExpenseEntity")
class ExpenseEntity {
    @PrimaryKey(autoGenerate = true)
    var TransId: Int = 0

    @ColumnInfo(name = "TransName")
    var TransName: String = ""

    @ColumnInfo(name = "TransDate")
    var TransDate: String = ""

    @ColumnInfo(name = "TransCategory")
    var TransCategory: String = ""

    @ColumnInfo(name = "TransAmount")
    var TransAmount: String = ""

}

//@Entity(tableName = "students")
//data class Student(
//    @NotNull var name: String,
//    @NotNull var password: String,
//    var subject: String,
//    var email: String
//
//) {
//
//    @PrimaryKey(autoGenerate = true)
//    var roll: Int = 0
//}
