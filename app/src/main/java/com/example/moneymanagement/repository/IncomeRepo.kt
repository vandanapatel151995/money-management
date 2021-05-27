package com.example.moneymanagement.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.moneymanagement.local.db.*
import java.util.Calendar.getInstance

class IncomeRepo(application: Application?) {
    private val incomeDAO: IncomeDAO
    private var allExpenses:List<IncomeEntity>?=null
  private  lateinit var appDataBase: AppDatabase

    //methods for database operations :-
    fun insert(income: IncomeEntity?) {
        if (incomeDAO != null) {
            InsertNoteAsyncTask(incomeDAO).execute(income)
        }
    }

    fun getAllIncome(): List<IncomeEntity>? {
        allExpenses = incomeDAO.getAllIncome()
        return allExpenses
    }
    fun getTotalIncomeAmount(): LiveData<Int>? {
        return  incomeDAO.getTotalIncomeAmount()
    }


class InsertNoteAsyncTask
 constructor(incomeDAO: IncomeDAO) :
        AsyncTask<IncomeEntity, Void?, Void?>() {
        //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private val incomeDAO: IncomeDAO
        init {
            this.incomeDAO = incomeDAO
        }

    override fun doInBackground(vararg params: IncomeEntity?): Void? {
        incomeDAO.saveIncome(params[0]!!)
      return null
    }




}

//    private class UpdateNoteAsyncTask private constructor(expenseDAO: ExpenseDAO) :
//        AsyncTask<ExpenseEntity?, Void?, Void?>() {
//        private val expenseDAO: ExpenseDAO
//        protected fun doInBackground(vararg notes: ExpenseEntity?): Void? {
//            expenseDAO.Update(notes[0])
//            return null
//        }
//
//        init { //constructor as the class is static
//            this.expenseDAO = expenseDAO
//        }
//    }

//    private class DeleteNoteAsyncTask private constructor(expenseDAO: ExpenseDAO) :
//        AsyncTask<ExpenseEntity?, Void?, Void?>() {
//        private val expenseDAO: ExpenseDAO
//        protected fun doInBackground(vararg notes: ExpenseEntity?): Void? {
//            expenseDAO.Delete(notes[0])
//            return null
//        }
//
//        init {
//            this.expenseDAO = expenseDAO
//        }
//    }

//    private class DeleteAllNotesAsyncTask private constructor(expenseDAO: ExpenseDAO) :
//        AsyncTask<Void?, Void?, Void?>() {
//        private val expenseDAO: ExpenseDAO
//        protected fun doInBackground(vararg voids: Void?): Void? {
//            expenseDAO.DeleteAllNotes()
//            return null
//        }
//
//        init {
//            this.expenseDAO = expenseDAO
//        }
//    }

    init { //application is subclass of context
        appDataBase= application?.let {
            Room.databaseBuilder(it, AppDatabase::class.java, "UserDB")
                .build()
        }!!
        incomeDAO = appDataBase.incomeDAO()
       //
    }
}