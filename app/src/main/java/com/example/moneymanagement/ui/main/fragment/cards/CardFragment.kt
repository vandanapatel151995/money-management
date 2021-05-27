package com.example.moneymanagement.ui.main.fragment.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.moneymanagement.model.PaymentsModel
import com.example.moneymanagement.R
import com.example.moneymanagement.local.db.CircleEntity
import com.example.moneymanagement.local.db.PaymentsEntity
import com.example.moneymanagement.ui.main.adapter.MyCircleAdapter
import com.example.moneymanagement.ui.main.adapter.PaymentsAdapter
import com.example.moneymanagement.ui.main.adapter.ViewsSliderAdapter


class CardFragment : Fragment(), MyCircleAdapter.ItemClickListener {

    // cards
    private lateinit var img_back_next: ImageView
    private lateinit var img_back_prev: ImageView
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var view_pager_card: ViewPager2
    private var cardAdapter: ViewsSliderAdapter? = null
    private lateinit var layouts: IntArray
    var viewModel: MyCardsViewModel? = null


    //  My circles
    private lateinit var mycircleAdapter: MyCircleAdapter
    lateinit var rv_circle: RecyclerView
    private lateinit var circleLayoutManager: LinearLayoutManager


    //payments
    private lateinit var paymentsAdapter: PaymentsAdapter
    private lateinit var paymentsLayoutManager: LinearLayoutManager
    lateinit var rv_payments: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater!!.inflate(R.layout.card_fragment, container, false)

        viewModel = MyCardsViewModel(activity!!.application)
        view_pager_card = view.findViewById<ViewPager2>(R.id.view_pager_card)

        rv_circle = view.findViewById<RecyclerView>(R.id.rv_circle)
        rv_payments = view.findViewById<RecyclerView>(R.id.rv_payments)
        btnPrevious = view.findViewById<Button>(R.id.btnPrevious)
        btnNext = view.findViewById<Button>(R.id.btnNext)
        img_back_prev = view.findViewById<ImageView>(R.id.img_back_prev)
        img_back_next = view.findViewById<ImageView>(R.id.img_back_next)
        rv_circle.setHasFixedSize(true)
        initCircles()

        getAllItemList()
        circleLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_circle.setLayoutManager(circleLayoutManager)
        paymentsLayoutManager = GridLayoutManager(activity, 2)
        rv_payments.setLayoutManager(paymentsLayoutManager)
        val rowListPayments: List<PaymentsModel> = getAllPaymentsList()
        initPayments(rowListPayments)
        initCards()
        return view
    }

    private fun initCircles() {
        for (i in 1..5) {
            var circles = CircleEntity()
            circles.PersonName = "user" + i
            circles.PersonPhoto = R.drawable.user
            viewModel!!.insertCirclePerson(circles)
        }
    }

    private fun initPayments(rowListPayments: List<PaymentsModel>) {
        for (item in rowListPayments) {
            var paymentsEntity = PaymentsEntity()
            paymentsEntity.PaymentType = item.payment_type
            paymentsEntity.PaymentTypeIcon = R.drawable.user
            viewModel!!.insertPaymentsType(paymentsEntity)
        }
        viewModel?.getAllPaymentTypeList()?.observe(viewLifecycleOwner, { paymentlist ->
            paymentsAdapter = PaymentsAdapter(activity!!.applicationContext, paymentlist)
            rv_payments.setAdapter(paymentsAdapter)
        })
    }

    private fun getAllPaymentsList(): List<PaymentsModel> {

        val allPaymentslist: MutableList<PaymentsModel> = ArrayList<PaymentsModel>()
        allPaymentslist.add(PaymentsModel("Electricity", R.drawable.user))
        allPaymentslist.add(PaymentsModel("Mobile Recharge", R.drawable.user))
        allPaymentslist.add(PaymentsModel("DTH Recharge", R.drawable.user))
        allPaymentslist.add(PaymentsModel("Insurance", R.drawable.user))
        allPaymentslist.add(PaymentsModel("Education", R.drawable.user))
        allPaymentslist.add(PaymentsModel("Water bill", R.drawable.user))
        allPaymentslist.add(PaymentsModel("GasOnline", R.drawable.user))
        return allPaymentslist
    }

    private fun getAllItemList() {
        viewModel!!.getAllCircleList()?.observe(viewLifecycleOwner, { rowListUser ->
            mycircleAdapter = MyCircleAdapter(activity!!.applicationContext, rowListUser)
            rv_circle.setAdapter(mycircleAdapter)
        })
//        mAdapter.setClickListener(this)


    }


    private fun initCards() {
        // layouts of all card sliders
        layouts = intArrayOf(
            R.layout.row_cards,
            R.layout.row_cards,
            R.layout.row_cards,
            R.layout.row_cards
        )
        cardAdapter = ViewsSliderAdapter(layouts)
        view_pager_card.setAdapter(cardAdapter)
        view_pager_card.registerOnPageChangeCallback(pageChangeCallback)

        btnNext.setOnClickListener { v ->
            // checking for last page
            val current: Int = getItem(+1)
            if (current < layouts.size) {
                // move to next screen
                view_pager_card.setCurrentItem(current)
            }
        }

        btnPrevious.setOnClickListener { v ->
            // checking for first page
            val current: Int = getItem(+1)
            if (current != 1) {
                // move to prev screen
                view_pager_card.setCurrentItem(current - 2)
            }
        }


    }


    /*
     * ViewPager page change listener
     */
    var pageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)


            if (position == layouts.size - 1) {
                img_back_next.setImageResource(R.drawable.circle_background_white)
                btnNext.setBackground(
                    ContextCompat.getDrawable(
                        activity!!.applicationContext,
                        R.drawable.arrow_forward
                    )
                )
                img_back_prev.setImageResource(R.drawable.circle_background)
                btnPrevious.setBackground(
                    ContextCompat.getDrawable(
                        activity!!.applicationContext,
                        R.drawable.arrow_back_white
                    )
                )
            } else {
                if (position != 0) {
                    img_back_prev.setImageResource(R.drawable.circle_background)
                    btnPrevious.setBackground(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.arrow_back_white
                        )
                    )
                    img_back_next.setImageResource(R.drawable.circle_background)
                    btnNext.setBackground(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.arrow_forward_white
                        )
                    )
                } else {
                    img_back_prev.setImageResource(R.drawable.circle_background_white)
                    btnPrevious.setBackground(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.arrow_back
                        )
                    )
                    img_back_next.setImageResource(R.drawable.circle_background)
                    btnNext.setBackground(
                        ContextCompat.getDrawable(
                            activity!!.applicationContext,
                            R.drawable.arrow_forward_white
                        )
                    )
                }

            }
        }
    }

    private fun getItem(i: Int): Int {
        return view_pager_card.getCurrentItem() + i
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun itemClick(view: View?, position: Int) {
        TODO("Not yet implemented")
    }

}