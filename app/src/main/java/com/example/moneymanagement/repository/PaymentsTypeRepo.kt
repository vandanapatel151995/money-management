package com.example.moneymanagement.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.moneymanagement.local.db.*

class PaymentsTypeRepo(application: Application) {
    private var paymentsDAO: PaymentsDAO
    private var list: LiveData<List<PaymentsEntity>>? = null
    private lateinit var appDataBase: AppDatabase

    //methods for database operations :-
    fun insert(paymentsEntity:  PaymentsEntity?) {
        InsertNoteAsyncTask(paymentsDAO).execute(paymentsEntity)
    }

    fun getAllPaymentsTypeList(): LiveData<List<PaymentsEntity>>? {
        list = paymentsDAO?.getAllPaymentsType()
        return list
    }


    class InsertNoteAsyncTask
    constructor(paymentsDAO: PaymentsDAO?) :
        AsyncTask<PaymentsEntity, Void?, Void?>() {
        //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private val paymentsDAO: PaymentsDAO

        init {
            this.paymentsDAO = paymentsDAO!!
        }

        override fun doInBackground(vararg params: PaymentsEntity?): Void? {
            paymentsDAO.savePaymentType(params[0]!!)
            return null
        }


    }

    init { //application is subclass of context
        appDataBase = application?.let {
            Room.databaseBuilder(it, AppDatabase::class.java, "UserDB")
                .build()
        }!!
        paymentsDAO = appDataBase.paymentsDAO()
        //  allExpenses!=expenseDAO!!.getAllExpenses()
    }
}