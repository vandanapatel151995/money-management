package com.example.moneymanagement.ui.main.fragment.cards

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneymanagement.local.db.CircleEntity
import com.example.moneymanagement.local.db.ExpenseEntity
import com.example.moneymanagement.local.db.PaymentsEntity
import com.example.moneymanagement.repository.CircleRepo
import com.example.moneymanagement.repository.PaymentsTypeRepo


class MyCardsViewModel(application: Application) : ViewModel() {
    private  var repository: CircleRepo?=null
    private  var paymentsTypeRepo: PaymentsTypeRepo?=null

    private  var list: LiveData<List<CircleEntity>>?=null
    private  var paymenttypeList: LiveData<List<PaymentsEntity>>?=null




    fun insertCirclePerson(expense: CircleEntity?) {
        repository!!.insert(expense)
    }
    fun getAllCircleList(): LiveData<List<CircleEntity>> {
        list = repository!!.getAllCircleList()
        return list!!
    }
    fun insertPaymentsType(expense: PaymentsEntity?) {
        paymentsTypeRepo!!.insert(expense)
    }
    fun getAllPaymentTypeList(): LiveData<List<PaymentsEntity>> {
        paymenttypeList = paymentsTypeRepo!!.getAllPaymentsTypeList()
        return paymenttypeList!!
    }




init {
    paymentsTypeRepo=PaymentsTypeRepo(application)
    repository = CircleRepo(application)
    list = repository!!.getAllCircleList()
}

}