package com.example.moneymanagement.ui.main.fragment.MyExpanse

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagement.R
import com.example.moneymanagement.local.db.ExpenseEntity
import com.example.moneymanagement.ui.main.adapter.TransactionAdapter
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet


class MyExpenseFragment : Fragment() {

    companion object {
        fun newInstance() = MyExpenseFragment()
    }

    private lateinit var spn_report: Spinner
    private lateinit var rv_transaction: RecyclerView

    var bubbleChart: BubbleChart? = null
    private lateinit var bubbleData: BubbleData
    private lateinit var bubbleDataSet: BubbleDataSet
    private lateinit var bubbleDataSet2: BubbleDataSet

    var bubbleEntriesIncome: ArrayList<BubbleEntry>? = null
    var bubbleEntriesExpense: ArrayList<BubbleEntry>? = null

    var spinnerEntries: ArrayList<String>? = null
    private var transAdapter: TransactionAdapter? = null
    var viewModel: MyExpenseViewModel? = null
    lateinit var txt_total_Expense: TextView
    lateinit var txt_total_income: TextView
    lateinit var txt_total_saving: TextView
    lateinit var txt_trans_nodata: TextView
    private var total_saving = 0
    private var total_expense = 0
    private var total_income = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.myexpense_fragment, container, false)
        bubbleChart = view.findViewById(R.id.BubbleChart);
        spn_report = view.findViewById(R.id.spn_report) as Spinner
        rv_transaction = view.findViewById<RecyclerView>(R.id.rv_transaction)
        txt_total_Expense = view.findViewById<TextView>(R.id.txt_total_Expense)
        txt_total_income = view.findViewById<TextView>(R.id.txt_total_income)
        txt_total_saving = view.findViewById<TextView>(R.id.txt_total_saving)
        txt_trans_nodata = view.findViewById<TextView>(R.id.txt_trans_nodata)
        val rowListTrans: List<ExpenseEntity>? = null
        var mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_transaction.setLayoutManager(mLayoutManager)
//        transAdapter = rowListTrans?.let { TransactionAdapter(activity!!.applicationContext, it) }
//        rv_transaction.setAdapter(transAdapter)
        getSpinnerEntries()
        getChartEntries(0)
        spn_report.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                var selectedItem: String = parent.adapter.getItem(position) as String
                getChartEntries(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })
//        bubbleDataSet = BubbleDataSet(bubbleEntriesIncome, "")
//        bubbleDataSet.setColors(activity!!.getColor(R.color.purple_500))
//        bubbleDataSet.setDrawValues(false)
//        bubbleDataSet2 = BubbleDataSet(bubbleEntriesExpense, "")
//        bubbleDataSet2.setColors(activity!!.getColor(R.color.yellow))
//        bubbleDataSet2.setDrawValues(false)
//
//        val dataSets: ArrayList<IBubbleDataSet> = ArrayList()
//        dataSets.add(bubbleDataSet) // add the data sets
//
//        dataSets.add(bubbleDataSet2)
//
//        bubbleData = BubbleData(dataSets)
//        bubbleChart!!.setData(bubbleData)
//        //   bubbleChart!!.setDrawMarkers(false)
//        bubbleChart!!.description.isEnabled = false

        //bubbleDataSet!!.setColors(ColorTemplate.JOYFUL_COLORS)
        viewModel = ViewModelProvider(this).get(MyExpenseViewModel::class.java)
        viewModel!!.MyExpenseViewModel(activity!!.application)
        getAllTransactionList()
        return view
    }

    private fun getAllTransactionList() {
//        val thread = Thread {
//
//            var expenselist = viewModel!!.getAllExpenses() as LiveData<List<ExpenseEntity>>
//
//        }

        viewModel?.getAllExpenses()?.observe(viewLifecycleOwner, Observer { newWord ->
            if (newWord.size != 0) {
                transAdapter = TransactionAdapter(activity!!.applicationContext, newWord)
                rv_transaction.setAdapter(transAdapter)
                newWord.forEach {
                    var name: String = it.TransName
                    Log.e("user.name", name)
                }
                rv_transaction.visibility = View.VISIBLE
                txt_trans_nodata.visibility = View.GONE
            } else {
                transAdapter = TransactionAdapter(activity!!.applicationContext, newWord)
                rv_transaction.setAdapter(transAdapter)
                rv_transaction.visibility = View.GONE
                txt_trans_nodata.visibility = View.VISIBLE
            }
            transAdapter = TransactionAdapter(activity!!.applicationContext, newWord)
            rv_transaction.setAdapter(transAdapter)
            newWord.forEach {
                var name: String = it.TransName
                Log.e("user.name", name)
            }

        })
        viewModel!!.getTotalExpensesAmount()?.observe(viewLifecycleOwner, { amount ->
            if (amount != null) {
                total_expense = amount
                txt_total_Expense.setText("$" + amount.toString())
                total_saving = total_income - total_expense
                txt_total_saving.setText("$" + total_saving)
            }
        })

        viewModel!!.getTotalIncomeAmount()?.observe(viewLifecycleOwner, { amount ->
            if (amount != null) {
                total_income = amount
                txt_total_income.setText("$" + amount.toString())
                total_saving = total_income - total_expense
                txt_total_saving.setText("$" + total_saving)
            }
        })
//        spn_report.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                var selectedItem: String = parent.adapter.getItem(position) as String
//                getChartEntries(position)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        })
        //    thread.start()
//        viewModel?.getAllExpenses()?.observe(this, Observer<List<ExpenseEntity>>() {
//            if (it != null) {
//                transAdapter = TransactionAdapter(activity!!.applicationContext, it)
//                rv_transaction.setAdapter(transAdapter)
//            }
//        })


        // val allPaymentslist: LiveData<List<TransactionsModel>>? = viewModel.getAllExpenses()

//        allPaymentslist.add(
//            TransactionsModel(
//                "tshirt",
//                "12/01/2021",
//                "Electricity",
//                100,
//                R.drawable.user
//            )
//        )
//        allPaymentslist.add(
//            TransactionsModel(
//                "pent",
//                "12/01/2021",
//                "Recharge",
//                100,
//                R.drawable.user
//            )
//        )
//        allPaymentslist.add(TransactionsModel("skirt", "12/01/2021", "DTH", 100, R.drawable.user))
//        allPaymentslist.add(
//            TransactionsModel(
//                "shirt",
//                "12/01/2021",
//                "Insurance",
//                100,
//                R.drawable.user
//            )
//        )
//        allPaymentslist.add(
//            TransactionsModel(
//                "dress",
//                "12/01/2021",
//                "Education",
//                100,
//                R.drawable.user
//            )
//        )
    }

    private fun getSpinnerEntries() {
        spinnerEntries = ArrayList()
        spinnerEntries!!.add("Year")
        spinnerEntries!!.add("Month")
        spinnerEntries!!.add("Week")

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            activity!!.getApplicationContext(),
            R.layout.row_spinner,
            R.id.textView,
            spinnerEntries!!
        )
        spn_report.setAdapter(adapter)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getChartEntries(position: Int) {
        activity!!.runOnUiThread {
            bubbleEntriesIncome = ArrayList()
            bubbleEntriesIncome!!.add(BubbleEntry(1.0f, 3.0f, 2f));
            bubbleEntriesIncome!!.add(BubbleEntry(2.0f, 3.3f, 0.2f));
            bubbleEntriesIncome!!.add(BubbleEntry(3.0f, 3.2f, 0.2f));
            bubbleEntriesIncome!!.add(BubbleEntry(4.0f, 3.3f, 0.2f));
            bubbleEntriesIncome!!.add(BubbleEntry(5.0f, 3.1f, 0.2f));


            bubbleEntriesExpense = ArrayList()
            bubbleEntriesExpense!!.add(BubbleEntry(1.0f, 2.0f, 0.2f));
            bubbleEntriesExpense!!.add(BubbleEntry(2.0f, 2.2f, 2f));
            bubbleEntriesExpense!!.add(BubbleEntry(3.0f, 2.2f, 2f));
            bubbleEntriesExpense!!.add(BubbleEntry(4.0f, 1.9f, 2f));
            bubbleEntriesExpense!!.add(BubbleEntry(5.0f, 2.5f, 2f));


//        bubbleEntries!!.add(BubbleEntry(2.0f, 3.0f, 3f));
//        bubbleEntries!!.add(BubbleEntry(2.0f, 4.0f, 1f));
//        bubbleEntries!!.add(BubbleEntry(3f, 5f, 2f));
//        bubbleEntries!!.add(BubbleEntry(4f, 6f, 3f));


            val xAxis = bubbleChart!!.xAxis
            xAxis.axisMinimum = 0f
            xAxis.axisMaximum = getYear(position)!!.size.toFloat()
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
            xAxis.setValueFormatter(IndexAxisValueFormatter(getYear(position)))
            xAxis.setDrawGridLines(false)

            val yAxis = bubbleChart!!.axisLeft
            yAxis.axisMinimum = 0f
            yAxis.axisMaximum = 5f
            yAxis.setValueFormatter(IndexAxisValueFormatter(getList()))
            bubbleChart!!.axisRight.isEnabled = false
            bubbleDataSet = BubbleDataSet(bubbleEntriesIncome, "")
            bubbleDataSet.setColors(activity!!.getColor(R.color.purple_500))
            bubbleDataSet.setDrawValues(false)
            bubbleDataSet2 = BubbleDataSet(bubbleEntriesExpense, "")
            bubbleDataSet2.setColors(activity!!.getColor(R.color.yellow))
            bubbleDataSet2.setDrawValues(false)

            val dataSets: ArrayList<IBubbleDataSet> = ArrayList()
            dataSets.add(bubbleDataSet) // add the data sets

            dataSets.add(bubbleDataSet2)

            bubbleData = BubbleData(dataSets)
            bubbleChart!!.setData(bubbleData)
            bubbleChart!!.setDrawMarkers(false)
            bubbleChart!!.description.isEnabled = false
            bubbleChart!!.legend.isEnabled = false
            bubbleChart!!.notifyDataSetChanged();
            bubbleChart!!.invalidate();
        }
    }

    private fun getList(): ArrayList<String>? {
        var label: ArrayList<String> = ArrayList<String>()
        label.add("$1K")
        label.add("$2K")
        label.add("$3K")
        label.add("$4K")
        label.add("$5K")
        return label


    }

    private fun getYear(position: Int): ArrayList<String>? {
        var label: ArrayList<String> = ArrayList<String>();
        if (position == 0) {

            label.add("Jan")
            label.add("Feb")
            label.add("Mar")
            label.add("Apr")
            label.add("May")
            label.add("June")
            label.add("July")
            label.add("Aug")
            label.add("Sep")
            label.add("Oct")
            label.add("Nov")
            label.add("Dec")
            return label;
        } else if (position == 1) {
            for (i in 1..30) {
                label.add(i.toString())
            }
            return label;
        } else if (position == 2) {


            label.add("Mon")
            label.add("Tue")
            label.add("Wed")
            label.add("Thus")
            label.add("Fri")
            label.add("Sat")
            label.add("Sun")

            return label;
        }
        return label

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}




