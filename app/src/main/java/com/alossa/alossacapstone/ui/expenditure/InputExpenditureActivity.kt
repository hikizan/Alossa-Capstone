package com.alossa.alossacapstone.ui.expenditure

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityInputExpenditureBinding
import com.alossa.alossacapstone.ui.home.HomeViewModel
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class InputExpenditureActivity : AppCompatActivity() {

    private var _binding: ActivityInputExpenditureBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: ViewModelFactory
    private lateinit var viewModelFromHome: HomeViewModel

    private val listIdAlocation = ArrayList<Int>()
    private val listNameAlocation = ArrayList<String>()
    private val listNominalAlocation = ArrayList<Int>()
    var getNameAlocationSelected: String? = null
    var getIdAlocationSelected: Int? = null
    var getNominalAlocationSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputExpenditureBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)
        supportActionBar?.title = "Input Pengeluaran"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sharedPref = SharedPref(root.context)
        factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]
        viewModelFromHome = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        listIdAlocation.add(0)
        listNameAlocation.add("Pilih Tipe Alokasi...")
        listNominalAlocation.add(0)
        viewModelFromHome.getAlokasiByIdUser(sharedPref.getId()).observe(this, { alocations ->
            if (alocations.isNotEmpty()) {
                val formatDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
                val thisMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1

                for (alokasiItem in alocations) {
                    val dt = formatDate.parse(alokasiItem.createdAt.toString())
                    val month = DateFormat.format("MM", dt) as String
                    val year = DateFormat.format("yyyy", dt) as String

                    if (year == thisYear.toString() && month == thisMonth.toString()){
                        listNameAlocation.add(alokasiItem.namaAlokasi.toString())
                        alokasiItem.id.let { listIdAlocation.add(it) }
                        listNominalAlocation.add(alokasiItem.nominal)
                    }

                }

                addItemsOnSpinner(listNameAlocation, listIdAlocation, listNominalAlocation)
            } else {
                Log.d("InputExpenditure", "onObserve: else: idUser = ${sharedPref.getId()} ")
            }
        })

        binding.btnSubmitExpenditure.setOnClickListener {
            val namaPengeluaran = binding.edtExpenditureName.text.toString().trim()
            val danaPengeluaran = binding.edtExpenditurePrice.text.toString().trim()
            if (getIdAlocationSelected == 0 || getIdAlocationSelected == null) {
                Toast.makeText(this, "Pilih Tipe Alokasi Dahulu!", Toast.LENGTH_SHORT).show()
            } else if (danaPengeluaran.isEmpty() || namaPengeluaran.isEmpty()) {
                Toast.makeText(this, "semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val idAlokasi = getIdAlocationSelected!!
                val idUser = sharedPref.getId()

                val updateNominalAlokasi: Int =
                    getNominalAlocationSelected.minus(danaPengeluaran.toInt())
                if (updateNominalAlokasi >= 0) {
                    viewModel.updateNominalAlokasi(
                        idAlokasi,
                        updateNominalAlokasi,
                        getNameAlocationSelected.toString()
                    ).observe(this, { response ->
                        if (response.status.equals("success")) {
                            Log.d(
                                "InputExpenditure",
                                "cekUpdateAlokasi success: idAlokasi = $idAlokasi \nupdateNominalAlokasi = $updateNominalAlokasi \ngetNameAloacationSelected = $getNameAlocationSelected"
                            )
                        } else {
                            Log.d(
                                "InputExpenditure",
                                "cekUpdateAlokasi notSuccess: idAlokasi = $idAlokasi \nupdateNominalAlokasi = $updateNominalAlokasi \ngetNameAloacationSelected = $getNameAlocationSelected \n\nstatus = ${response.status}"
                            )
                        }
                    })

                    viewModel.addPengeluaran(
                        idUser, idAlokasi, danaPengeluaran.toInt(),
                        namaPengeluaran
                    ).observe(this, { response ->
                        Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
                        if (response.status.equals("success")) {
                            //val intent = Intent(applicationContext, ExpenditureFragment::class.java)
                            //startActivity(intent)
                            finish()
                        }
                    })

                } else {
                    Toast.makeText(
                        this,
                        "Sisa dana pada alokasi ${getNameAlocationSelected} \ntidak mencukupi",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }

    fun addItemsOnSpinner(
        mListNameAlocation: ArrayList<String>,
        mListIdAlocation: ArrayList<Int>,
        mListNominalAlocation: ArrayList<Int>
    ) {
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, mListNameAlocation
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAlocationType.setAdapter(dataAdapter)

        binding.spinnerAlocationType.setPrompt("Pilih Tipe Alokasi")

        binding.spinnerAlocationType.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                getNameAlocationSelected = mListNameAlocation[position]
                getIdAlocationSelected = mListIdAlocation[position]
                getNominalAlocationSelected = mListNominalAlocation[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                binding.spinnerAlocationType
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}