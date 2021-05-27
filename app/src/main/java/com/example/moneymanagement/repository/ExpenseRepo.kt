package com.example.moneymanagement.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.moneymanagement.local.db.AppDatabase
import com.example.moneymanagement.local.db.ExpenseDAO
import com.example.moneymanagement.local.db.ExpenseEntity

class ExpenseRepo(application: Application) {
    private var expenseDAO: ExpenseDAO
    private var allExpenses: LiveData<List<ExpenseEntity>>? = null
    private lateinit var appDataBase: AppDatabase

    //methods for database operations :-
    fun insert(expense: ExpenseEntity?) {
        InsertNoteAsyncTask(expenseDAO).execute(expense)
    }

    fun getAllExpenses(): LiveData<List<ExpenseEntity>>? {
        allExpenses = expenseDAO?.getAllExpenses()
        return allExpenses
    }

    fun getTotalExpensesAmount(): LiveData<Int>? {
        return expenseDAO.getTotalExpensesAmount()
    }

    class InsertNoteAsyncTask
    constructor(expenseDAO: ExpenseDAO?) :
        AsyncTask<ExpenseEntity, Void?, Void?>() {
        //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private val expenseDAO: ExpenseDAO

        init {
            this.expenseDAO = expenseDAO!!
        }

        override fun doInBackground(vararg params: ExpenseEntity?): Void? {
            expenseDAO.saveExpense(params[0]!!)
            return null
        }


    }

    init { //application is subclass of context
        appDataBase = application?.let {
            Room.databaseBuilder(it, AppDatabase::class.java, "UserDB")
                .build()
        }!!
        expenseDAO = appDataBase.expenseDAO()
        //  allExpenses!=expenseDAO!!.getAllExpenses()
    }
}