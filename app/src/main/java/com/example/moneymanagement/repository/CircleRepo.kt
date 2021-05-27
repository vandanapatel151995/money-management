package com.example.moneymanagement.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.moneymanagement.local.db.*

class CircleRepo(application: Application) {
    private var cicleDAO: CicleDAO
    private var allCircleList: LiveData<List<CircleEntity>>? = null
    private lateinit var appDataBase: AppDatabase

    //methods for database operations :-
    fun insert(circleEntity:  CircleEntity?) {
        InsertNoteAsyncTask(cicleDAO).execute(circleEntity)
    }

    fun getAllCircleList(): LiveData<List<CircleEntity>>? {
        allCircleList = cicleDAO?.getAllCircleList()
        return allCircleList
    }


    class InsertNoteAsyncTask
    constructor(cicleDAO: CicleDAO?) :
        AsyncTask<CircleEntity, Void?, Void?>() {
        //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private val cicleDAO: CicleDAO

        init {
            this.cicleDAO = cicleDAO!!
        }

        override fun doInBackground(vararg params: CircleEntity?): Void? {
            cicleDAO.saveCircle(params[0]!!)
            return null
        }


    }

    init { //application is subclass of context
        appDataBase = application?.let {
            Room.databaseBuilder(it, AppDatabase::class.java, "UserDB")
                .build()
        }!!
        cicleDAO = appDataBase.circleDAO()
        //  allExpenses!=expenseDAO!!.getAllExpenses()
    }
}