package com.example.moneymanagement.ui.main.fragment.AddExpense

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneymanagement.local.db.ExpenseEntity
import com.example.moneymanagement.repository.ExpenseRepo


class AddExpenseViewModel : ViewModel() {
    private lateinit var repository: ExpenseRepo
    fun AddExpenseViewModel(application: Application) {
        repository = ExpenseRepo(application)
    }


    fun insertExpense(expense: ExpenseEntity?) {
        repository!!.insert(expense)
    }

    fun isEmpty(expense: ExpenseEntity?): String {
        if (expense!!.TransName.isEmpty()) {
            return "please enter name"
        } else if (expense!!.TransDate.isEmpty()) {
            return "please enter date"
        }else if (expense!!.TransCategory.isEmpty()) {
            return "please enter category"
        }else if (expense!!.TransAmount.isEmpty()) {
            return "please enter amount"
        }
        return ""
    }



}