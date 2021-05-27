package com.example.moneymanagement.ui.main.fragment.AddIncome

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moneymanagement.R
import com.example.moneymanagement.local.db.AppDatabase
import com.example.moneymanagement.local.db.ExpenseEntity
import com.example.moneymanagement.local.db.IncomeEntity
import kotlinx.android.synthetic.main.add_income_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class AddIncomeFragment : Fragment() {





    private lateinit var viewModel: AddIncomeViewModel
    private lateinit var edtIncomeType: EditText
    private lateinit var edtIncomeAmount: EditText
    private lateinit var btnAddIncome: Button
    private lateinit var edtIncomeDate: EditText
    lateinit var appDataBase: AppDatabase

    var spinnerEntries: ArrayList<String>? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.add_income_fragment, container, false)
        edtIncomeType = view.findViewById(R.id.edtIncomeType)
        edtIncomeAmount = view.findViewById(R.id.edtIncomeAmount)
        btnAddIncome = view.findViewById(R.id.btnAddIncome)
        edtIncomeDate = view.findViewById<EditText>(R.id.edtIncomeDate)
        val myCalendar = Calendar.getInstance()

        var date: OnDateSetListener? =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                var myFormat: String = "dd/MM/yyyy" //In which you need put here
                var sdf: SimpleDateFormat = SimpleDateFormat(myFormat, Locale.US);
                edtIncomeDate.setText(sdf.format(myCalendar.getTime()));
            }
        edtIncomeDate.setOnClickListener {
            DatePickerDialog(
                context!!, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnAddIncome.setOnClickListener {


            var name = edtIncomeType.text.toString()
            var date = edtIncomeDate.text.toString()
            var amount = edtIncomeAmount.text.toString()
            var incomeEntity = IncomeEntity(name, date, amount.toInt())
            var message: String = viewModel.isEmpty(incomeEntity)
            if (message.equals("")) {
                viewModel.insertExpense(incomeEntity)
                var incomelist: List<IncomeEntity>? = null


                val thread = Thread {
                    incomelist = viewModel.getAllincome() as List<IncomeEntity>?
                    incomelist!!.forEach {

                        Log.e("income.data.", it.IncomeType)

                    }
                }
                thread.start()
            clearText()
                Toast.makeText(activity, "Income Added Successfully.", Toast.LENGTH_SHORT)
        } else
        Toast.makeText(activity, message, Toast.LENGTH_SHORT)
    }
    return view
}

private fun clearText() {
    edtIncomeType.setText("")
    edtIncomeAmount.setText("")
    edtIncomeDate.setText("")
}


override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(AddIncomeViewModel::class.java)
    viewModel.AddIncomeViewModel(activity!!.application)
}

}


