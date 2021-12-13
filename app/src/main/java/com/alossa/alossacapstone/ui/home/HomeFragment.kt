package com.alossa.alossacapstone.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

    var typeAlokasi: Array<String> = arrayOf()
    var nominalAlokasi: Array<Int> = arrayOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        anyCart = binding.cart

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        val alocationAdapter = AlocationAdapter()

        viewModel.getAlokasiByIdUser(2).observe(viewLifecycleOwner, { alocations ->
            if (alocations != null){
                alocationAdapter.setAlocation(alocations)
                alocationAdapter.notifyDataSetChanged()

                for (alokasiItem in alocations){
                    typeAlokasi += alokasiItem.namaAlokasi.toString()
                    nominalAlokasi += alokasiItem.nominal?.toInt() ?: 0
                }

                setupPieCart(typeAlokasi,nominalAlokasi)

            }

        })
        Log.d("HomeFragment", "onCreateView: check value in typeAlokasi = $typeAlokasi \n dan nominal Alokasi = $nominalAlokasi")

        binding.rvAlocation.layoutManager = LinearLayoutManager(context)
        binding.rvAlocation.setHasFixedSize(true)
        binding.rvAlocation.adapter = alocationAdapter

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setupPieCart(mTypeAlokasi: Array<String>, mNominalAlokasi: Array<Int>){

        var pie = AnyChart.pie()
        var dataEntrie = ArrayList<DataEntry>()
        for (item in mTypeAlokasi.indices){
            dataEntrie.add(ValueDataEntry(mTypeAlokasi[item], mNominalAlokasi[item]))
        }

        pie.data(dataEntrie)
        anyCart.setChart(pie)

    }


}