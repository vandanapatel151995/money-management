package com.example.moneymanagement.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "PaymentsEntity")
class PaymentsEntity {
    @PrimaryKey(autoGenerate = true)
    var PaymentTypeID: Int = 0

    @ColumnInfo(name = "PaymentType")
    var PaymentType: String = ""

    @ColumnInfo(name = "PaymentTypeIcon")
    var PaymentTypeIcon: Int = 0


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
