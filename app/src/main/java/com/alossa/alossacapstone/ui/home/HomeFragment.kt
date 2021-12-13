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


    //var month = arrayOf("Jan", "Feb", "Mar")
    //var dana = arrayOf(200,100, 300)

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

                /*
                for (itemString in alocationAdapter.listTypeAlocation){
                    typeAlokasi += itemString
                }

                for (itemInt in alocationAdapter.listNominalAlcation){
                    nominalAlokasi += itemInt
                }
                 */


                for (alokasiItem in alocations.indices){
                    //typeAlokasi += alokasiItem.namaAlokasi.toString()
                    //nominalAlokasi += alokasiItem.nominal?.toInt() ?: 0
                    typeAlokasi.set(alokasiItem,alocations[alokasiItem].namaAlokasi.toString())
                    nominalAlokasi.set(alokasiItem, alocations[alokasiItem].nominal!!.toInt())
                }


                Log.d("HomeFragment", "onCreateView: ambil dari observe: TypeAlokasi = ${typeAlokasi} \n alocations = $alocations")



            }

        })
        Log.d("HomeFragment", "onCreateView: check value in typeAlokasi = $typeAlokasi \n dan nominal Alokasi = $nominalAlokasi")

        binding.rvAlocation.layoutManager = LinearLayoutManager(context)
        binding.rvAlocation.setHasFixedSize(true)
        binding.rvAlocation.adapter = alocationAdapter

        /*
        for (itemString in alocationAdapter.listTypeAlocation){
            typeAlokasi += itemString
            Log.d("HomeFragment", "onCreateView: check in for typeAlokasi = $typeAlokasi")
        }

        for (itemInt in alocationAdapter.listNominalAlcation){
            nominalAlokasi += itemInt
        }
         */

        setupPieCart(typeAlokasi,nominalAlokasi)

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
            Log.d("HomeFragmentSetupPie", "setupPieCart: mTypeAlokasi per item = ${mTypeAlokasi[item]}")
        }

        pie.data(dataEntrie)
        anyCart.setChart(pie)

    }


}