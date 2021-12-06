package com.alossa.alossacapstone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.databinding.FragmentHomeBinding
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var anyCart: AnyChartView

    var month = arrayOf("Jan", "Feb", "Mar")
    var dana = arrayOf(200,100, 300)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        anyCart = binding.cart

        setupPieCart()
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setupPieCart(){
        var pie = AnyChart.pie()
        var dataEntrie = ArrayList<DataEntry>()
        for (item in month.indices){
            dataEntrie.add(ValueDataEntry(month[item], dana[item]))
        }

        pie.data(dataEntrie)
        anyCart.setChart(pie)

    }


}