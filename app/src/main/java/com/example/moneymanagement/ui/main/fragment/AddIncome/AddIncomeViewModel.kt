package com.example.moneymanagement.ui.main.fragment.AddIncome

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moneymanagement.local.db.ExpenseEntity
import com.example.moneymanagement.local.db.IncomeEntity
import com.example.moneymanagement.repository.ExpenseRepo
import com.example.moneymanagement.repository.IncomeRepo


class AddIncomeViewModel : ViewModel() {
    private var repository: IncomeRepo? = null
    private var allIncome: List<IncomeEntity?>? = null
    fun AddIncomeViewModel(application: Application) {
        //super(application)
        repository = IncomeRepo(application)
        //allExpenses = repository!!.getAllExpenses()
    }


    fun insertExpense(incomeEntity: IncomeEntity?) {
        repository!!.insert(incomeEntity)
    }

    fun isEmpty(expense: IncomeEntity?): String {
        if (expense!!.IncomeType.isEmpty()) {
            return "please enter income type"
        } else if (expense!!.IncomeDate.isEmpty()) {
            return "please enter date"
        }else if (expense!!.IncomeAmount==0) {
            return "please enter amount"
        }
        return ""
    }


    fun getAllincome(): List<IncomeEntity?>? {
        allIncome=repository!!.getAllIncome()
        return allIncome
    }
}