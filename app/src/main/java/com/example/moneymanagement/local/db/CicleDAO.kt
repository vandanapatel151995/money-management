package com.example.moneymanagement.local.db

import androidx.annotation.IntegerRes
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CicleDAO {
    @Insert
    fun saveCircle(circleEntity: CircleEntity)

    @Query(value = "Select * from CircleEntity")
    fun getAllCircleList(): LiveData<List<CircleEntity>>

}
