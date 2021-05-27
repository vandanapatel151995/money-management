package com.example.moneymanagement.ui.main.fragment.MyExpanse

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneymanagement.local.db.ExpenseEntity
import com.example.moneymanagement.repository.ExpenseRepo
import com.example.moneymanagement.repository.IncomeRepo


class MyExpenseViewModel : ViewModel() {
    private  var repository: ExpenseRepo?=null
    private  var incomeRepo: IncomeRepo?=null

    private  var allExpenses: LiveData<List<ExpenseEntity>>?=null
    fun MyExpenseViewModel(application: Application) {
        //super(application)
        repository = ExpenseRepo(application)
        incomeRepo= IncomeRepo(application)
      allExpenses = repository!!.getAllExpenses()
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


    fun getAllExpenses(): LiveData<List<ExpenseEntity>> {
        allExpenses = repository!!.getAllExpenses()
        return allExpenses!!
    }


    fun getTotalExpensesAmount():LiveData<Int>?{
      return  repository!!.getTotalExpensesAmount()
    }
    fun getTotalIncomeAmount():LiveData<Int>?{
        return  incomeRepo!!.getTotalIncomeAmount()
    }
}