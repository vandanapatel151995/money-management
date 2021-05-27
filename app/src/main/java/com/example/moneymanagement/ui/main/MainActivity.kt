package com.example.moneymanagement.ui.main

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanagement.R
import com.example.moneymanagement.model.NavigationItemModel
import com.example.moneymanagement.ui.main.adapter.NavigationRVAdapter
import com.example.moneymanagement.ui.main.fragment.AddExpense.AddExpenseFragment
import com.example.moneymanagement.ui.main.fragment.AddIncome.AddIncomeFragment
import com.example.moneymanagement.ui.main.fragment.cards.CardFragment
import com.example.moneymanagement.ui.main.fragment.MyExpanse.MyExpenseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {


    var navigation_rv: RecyclerView? = null
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter
    var menu_icon: FloatingActionButton? = null
    private var doubleBackToExitPressedOnce = false
    private var items = arrayListOf(
        NavigationItemModel(R.drawable.icn_transaction, "My Expenses"),
        NavigationItemModel(R.drawable.icn_card, "cards"),
        NavigationItemModel(R.drawable.icn_card, "Add Expense"),
        NavigationItemModel(R.drawable.icn_card, "Add Income"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigation_rv = findViewById<RecyclerView>(R.id.navigation_rv)
        menu_icon = findViewById<FloatingActionButton>(R.id.menu_icon)

        // Set the toolbar
     //   setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        navigation_rv!!.layoutManager = LinearLayoutManager(this)
        navigation_rv!!.setHasFixedSize(true)

        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        val homeFragment = MyExpenseFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()
        // Add Item Touch Listener

        menu_icon!!.setOnClickListener {
            runOnUiThread {
            drawerLayout.openDrawer(GravityCompat.START)}
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                runOnUiThread(Runnable { })
//                drawerLayout.closeDrawer(GravityCompat.START)
//
//            } else {
//            //    runOnUiThread {
//                    drawerLayout.openDrawer(GravityCompat.START)
//              //  }
////                Handler().postDelayed({
////
////                    drawerLayout.openDrawer(GravityCompat.START)
////                }, 200)
//
//            }
        }
        // Set Header Image
//        navigation_header_img.setImageResource(R.drawable.logo)
//
//        // Set background of Drawer
//        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        navigation_rv!!.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // # My Expense Fragment
                        val homeFragment = MyExpenseFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()

                        menu_icon!!.visibility=View.VISIBLE
                    }
                    1 -> {
                        // # card Fragment
                        val cardFragment = CardFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, cardFragment).commit()

                        menu_icon!!.visibility=View.GONE
                    }
                    2 -> {
                        // # Add Expense Fragment
                        val addExpenseFragment = AddExpenseFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, addExpenseFragment).commit()

                        menu_icon!!.visibility=View.GONE
                    }
                    3 -> {
                        // # My Expense Fragment
                        val addIncomeFragment = AddIncomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, addIncomeFragment).commit()

                        menu_icon!!.visibility=View.GONE
                    }

                }
                    updateAdapter(position)
                Handler().postDelayed({

                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))


        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv!!.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        val homeFragment = MyExpenseFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()

        menu_icon!!.visibility=View.VISIBLE
    }
}
