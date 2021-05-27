package com.example.moneymanagement.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "CircleEntity")
class CircleEntity {
    @PrimaryKey(autoGenerate = true)
    var PersonId: Int = 0

    @ColumnInfo(name = "PersonName")
    var PersonName: String = ""

    @ColumnInfo(name = "PersonPhoto")
    var PersonPhoto: Int = 0


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
