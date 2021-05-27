package com.example.moneymanagement.ui.main.fragment.AddExpense

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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddExpenseFragment : Fragment() {


    private lateinit var viewModel: AddExpenseViewModel
    private lateinit var edtItemName: EditText
    private lateinit var edtDate: EditText
    private lateinit var edtCategory: EditText
    private lateinit var edtAmount: EditText
    private lateinit var btnAddExpense: Button
    private lateinit var spn_trans_category: Spinner

    var spinnerEntries: ArrayList<String>? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.add_expense_fragment, container, false)
        edtItemName = view.findViewById(R.id.edtItemName)
        edtDate = view.findViewById(R.id.edtDate)
        edtCategory = view.findViewById(R.id.edtCategory)
        edtAmount = view.findViewById(R.id.edtAmount)
        btnAddExpense = view.findViewById(R.id.btnAddExpense)
        spn_trans_category = view.findViewById(R.id.spn_trans_category)
        getSpinnerEntries()
        edtCategory.setOnClickListener {
            spn_trans_category.performClick()
        }

        spn_trans_category.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var selectedItem: String = parent.adapter.getItem(position) as String
                edtCategory.setText(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        val myCalendar = Calendar.getInstance()

        var date: OnDateSetListener? =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                var myFormat: String = "dd/MM/yyyy"
                var sdf: SimpleDateFormat = SimpleDateFormat(myFormat, Locale.US);
                edtDate.setText(sdf.format(myCalendar.getTime()));
            }
        edtDate.setOnClickListener {
            DatePickerDialog(
                context!!, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnAddExpense.setOnClickListener {


            var name = edtItemName.text.toString()
            var date = edtDate.text.toString()
            var amount = edtAmount.text.toString()
            var category = edtCategory.text.toString()


            var expenseEntity = ExpenseEntity()
            expenseEntity.TransId=0
            expenseEntity.TransName=name
            expenseEntity.TransCategory=category
            expenseEntity.TransDate=date
            expenseEntity.TransAmount=amount
            var message: String = viewModel.isEmpty(expenseEntity)
            if (message.equals("")) {
                viewModel.insertExpense(expenseEntity)
                clearText()
                Toast.makeText(activity, "Expense Added Successfully.", Toast.LENGTH_SHORT)

            } else
                Toast.makeText(activity, message, Toast.LENGTH_SHORT)



        }
        return view
    }

    private fun clearText() {
       edtAmount.setText("")
        edtDate.setText("")
        edtItemName.setText("")
        spn_trans_category.setSelection(0)
    }

    private fun getSpinnerEntries() {
        spinnerEntries = ArrayList()
        spinnerEntries!!.add("electricity")
        spinnerEntries!!.add("Gas Online")
        spinnerEntries!!.add("Recharge")
        spinnerEntries!!.add("Personal Loan")
        spinnerEntries!!.add("Bus Tickets")
        spinnerEntries!!.add("Flight Ticket")

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            activity!!.getApplicationContext(),
            R.layout.row_spinner,
            R.id.textView,
            spinnerEntries!!
        )
        spn_trans_category.setAdapter(adapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddExpenseViewModel::class.java)
        viewModel.AddExpenseViewModel(activity!!.application)
    }

}


