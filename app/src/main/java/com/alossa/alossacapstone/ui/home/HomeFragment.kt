package com.alossa.alossacapstone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.databinding.FragmentHomeBinding
import com.alossa.alossacapstone.utils.ViewModelFactory
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var anyCart: AnyChartView

    private lateinit var adapter: AlocationAdapter
    private lateinit var listAlokasi: List<Alokasi>
    private val listAlocations: List<Alokasi> = ArrayList()
    val factory = ViewModelFactory.getInstance()
    val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]


    var month = arrayOf("Jan", "Feb", "Mar")
    var dana = arrayOf(200,100, 300)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPieCart()

        viewModel.getAlokasiByIdUser(2).observe(viewLifecycleOwner, { response ->
            if (response != null){
                adapter = AlocationAdapter(response)
                binding.rvAlocation.layoutManager = LinearLayoutManager(context)
                binding.rvAlocation.adapter = adapter
                adapter.notifyDataSetChanged()
            }


        })

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