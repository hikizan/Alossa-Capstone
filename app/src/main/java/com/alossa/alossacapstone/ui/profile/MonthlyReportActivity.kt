package com.alossa.alossacapstone.ui.profile

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityMonthlyReportBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory
import java.util.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alossa.alossacapstone.adapter.AlokasiAdapter


class MonthlyReportActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfilViewModel
    private lateinit var binding: ActivityMonthlyReportBinding
    private lateinit var alokasiAdapter : AlokasiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonthlyReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Laporan Bulanan"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ProfilViewModel::class.java]
        val sharedPref = SharedPref(this)
        val spinMonth = binding.monthSpinner
        alokasiAdapter = AlokasiAdapter()

        val years = ArrayList<String>()
        val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        val thisMonth: Int = Calendar.getInstance().get(Calendar.MONTH)+1
        var year = thisYear
        var month = thisMonth

        for (i in 2010..thisYear) {
            years.add(i.toString())
        }

        val adapterYear = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinYear = binding.yearspin
        spinYear.adapter = adapterYear

        with(binding.rvExpenditure) {
            layoutManager = LinearLayoutManager(context)
            adapter = alokasiAdapter
        }


        spinYear.setSelection(years.lastIndex)
        spinMonth.setSelection(thisMonth-1)
        spinYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                year = spinYear.selectedItem.toString().toInt()
                initData(sharedPref.getId(), month, year)
            }

        }

        spinMonth.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                month = position+1
                initData(sharedPref.getId(), month, year)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    @SuppressLint("SetTextI18n")
    fun initData(idUser:Int, month:Int, year: Int){

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getLaporanBulanan(idUser, month, year)
            .observe(this@MonthlyReportActivity, { laporan ->
                if (laporan.content==404){
                    Toast.makeText(this, laporan.msg, Toast.LENGTH_LONG
                    ).show()
                    binding.apply {
                        notEmtpy.visibility = View.GONE
                        empty.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }
                }else{
                    binding.apply {
                        notEmtpy.visibility = View.VISIBLE
                        empty.visibility = View.GONE
                        tvMonthlyExpenditureTotal.text = "Rp.${laporan.pengeluaran}"
                        tvMonthlyIncome.text = "Rp.${laporan.pemasukan}"
                        tvMonthlySisa.text = "Rp.${laporan.sisa}"
                        alokasiAdapter.setAlokasi(laporan.alokasi)
                        progressBar.visibility = View.GONE
                    }
                }
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

