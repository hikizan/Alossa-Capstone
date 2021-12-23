package com.alossa.alossacapstone.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
import android.text.format.DateFormat
import com.alossa.alossacapstone.data.model.Alokasi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var anyCart: AnyChartView
    private lateinit var root: View

    private lateinit var topProgressBar: ProgressBar
    private lateinit var bottomProgressBar: ProgressBar
    private lateinit var viewModel: HomeViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var sharedPref: SharedPref
    private lateinit var alocationAdapter: AlocationAdapter
    private var listNowAlocation = ArrayList<Alokasi>()

    private var typeAlokasi: Array<String> = arrayOf()
    private var nominalAlokasi: Array<Int> = arrayOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding.root

        anyCart = binding.cart
        topProgressBar = binding.progressBarTop
        bottomProgressBar = binding.progressBarBottom

        factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        alocationAdapter = AlocationAdapter()
        sharedPref = SharedPref(root.context)

        binding.btnInputdata.setOnClickListener {
            val moveToAddExpenditure = Intent(context, AddExpenditureActivity::class.java)
            startActivity(moveToAddExpenditure)
        }

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        viewModel.getPemasukanByIdUser(sharedPref.getId())
            .observe(viewLifecycleOwner, { pemasukan ->
                if (pemasukan.isEmpty()){
                    setLayoutVisible(false)
                    topProgressBar.visibility = View.INVISIBLE
                    bottomProgressBar.visibility = View.INVISIBLE
                }else{
                    for (itemPemasukan in pemasukan) {
                        val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        val dt = format1.parse(itemPemasukan.createdAt.toString())

                        val month = DateFormat.format("MM", dt) as String
                        val year = DateFormat.format("yyyy", dt) as String
                        val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
                        val thisMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1

                        if (year == thisYear.toString() && month == thisMonth.toString() && itemPemasukan.status == 1) {
                            setLayoutVisible(true)
                            viewModel.getAlokasiByIdUser(sharedPref.getId())
                                .observe(viewLifecycleOwner, { alocations ->
                                    if (alocations.isNotEmpty()) {
                                        for (searchNowAlocation in alocations){
                                            val dtAlokasi = format1.parse(searchNowAlocation.createdAt.toString())

                                            val monthAlokasi = DateFormat.format("MM", dtAlokasi) as String
                                            val yearAlokasi = DateFormat.format("yyyy", dtAlokasi) as String

                                            if (yearAlokasi == thisYear.toString() && monthAlokasi == thisMonth.toString()){
                                                listNowAlocation.add(searchNowAlocation)
                                            }
                                        }


                                        alocationAdapter.setAlocation(listNowAlocation)
                                        alocationAdapter.notifyDataSetChanged()
                                        for (alokasiItem in listNowAlocation) {
                                            typeAlokasi += alokasiItem.namaAlokasi.toString()
                                            nominalAlokasi += alokasiItem.nominal

                                        }
                                        setupPieCart(typeAlokasi, nominalAlokasi)
                                        topProgressBar.visibility = View.INVISIBLE
                                        bottomProgressBar.visibility = View.INVISIBLE
                                    } else {
                                        topProgressBar.visibility = View.INVISIBLE
                                        bottomProgressBar.visibility = View.INVISIBLE
                                    }
                                })
                        } else {
                            setLayoutVisible(false)
                            topProgressBar.visibility = View.INVISIBLE
                            bottomProgressBar.visibility = View.INVISIBLE
                        }
                    }
                }

            })

        binding.rvAlocation.layoutManager = LinearLayoutManager(context)
        binding.rvAlocation.setHasFixedSize(true)
        binding.rvAlocation.adapter = alocationAdapter
    }

    private fun setupPieCart(mTypeAlokasi: Array<String>, mNominalAlokasi: Array<Int>) {

        val pie = AnyChart.pie()
        val dataEntrie = ArrayList<DataEntry>()
        for (item in mTypeAlokasi.indices) {
            dataEntrie.add(ValueDataEntry(mTypeAlokasi[item], mNominalAlokasi[item]))
        }

        pie.data(dataEntrie)
        anyCart.setChart(pie)

    }

    private fun setLayoutVisible(isFill: Boolean) {
        if (isFill) {
            binding.layoutIfnotnull.visibility = View.VISIBLE
            binding.layoutIfnull.visibility = View.GONE
        } else {
            binding.layoutIfnotnull.visibility = View.INVISIBLE
            binding.layoutIfnull.visibility = View.VISIBLE
        }
    }


}