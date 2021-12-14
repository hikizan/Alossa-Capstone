package com.alossa.alossacapstone.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alossa.alossacapstone.databinding.FragmentHomeBinding
import com.alossa.alossacapstone.utils.SharedPref
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
        val sharedPref = SharedPref(root.context)

        viewModel.getAlokasiByIdUser(sharedPref.getId()).observe(viewLifecycleOwner, { alocations ->

            if (alocations.isNotEmpty()){
                alocationAdapter.setAlocation(alocations)
                alocationAdapter.notifyDataSetChanged()
                for (alokasiItem in alocations) {
                    typeAlokasi += alokasiItem.namaAlokasi.toString()
                    nominalAlokasi += alokasiItem.nominal?.toInt() ?: 0
                }
                setLayoutVisible(true)
                setupPieCart(typeAlokasi,nominalAlokasi)
            } else {
                setLayoutVisible(false)
            }

        })

        binding.rvAlocation.layoutManager = LinearLayoutManager(context)
        binding.rvAlocation.setHasFixedSize(true)
        binding.rvAlocation.adapter = alocationAdapter

        binding.btnInputdata.setOnClickListener {
            val moveToAddExpenditure = Intent(context,AddExpenditureActivity::class.java)
            startActivity(moveToAddExpenditure)
        }

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

    fun setLayoutVisible(isFill: Boolean){
        if (isFill){
            binding.layoutIfnotnull.visibility = View.VISIBLE
            binding.layoutIfnull.visibility = View.GONE
        } else {
            binding.layoutIfnotnull.visibility = View.INVISIBLE
            binding.layoutIfnull.visibility = View.VISIBLE
        }
    }


}