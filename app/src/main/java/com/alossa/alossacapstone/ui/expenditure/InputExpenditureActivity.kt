package com.alossa.alossacapstone.ui.expenditure

import android.os.Bundle
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


class InputExpenditureActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputExpenditureBinding

    private lateinit var factory: ViewModelFactory
    private lateinit var viewModelFromHome: HomeViewModel

    private val listIdAlocation = ArrayList<Int>()
    private val listNameAlocation = ArrayList<String>()
    var number: Int? = null
    var getStringFromDropdown: String? = null
    var getIdAlocationSelected: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputExpenditureBinding.inflate(layoutInflater)
        val root: View = _binding.root
        setContentView(root)
        supportActionBar?.title = "Input Pengeluaran"

        val sharedPref = SharedPref(root.context)
        factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]
        viewModelFromHome = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        listIdAlocation.add(0)
        listNameAlocation.add("Pilih Tipe Alokasi...")
        viewModelFromHome.getAlokasiByIdUser(sharedPref.getId()).observe(this,{ alocations ->
            if (alocations.isNotEmpty()){
                for (alokasiItem in alocations){
                    listNameAlocation.add(alokasiItem.namaAlokasi.toString())
                    alokasiItem.id?.let { listIdAlocation.add(it) }
                    Log.d("InputExpenditure", "onCreate: Check idAlokasi = ${alokasiItem.id} ")
                }
                Log.d("InputExpenditure", "onObserve: listAlokasiName = $listNameAlocation \n listAlokasiId = $listIdAlocation \n" +
                        "alocations = $alocations")

                addItemsOnSpinner(listNameAlocation, listIdAlocation)
            }
        })

        _binding.btnSubmitExpenditure.setOnClickListener {

            if (getIdAlocationSelected == 0){
                Toast.makeText(this, "Pilih Tipe Alokasi Dahulu!", Toast.LENGTH_SHORT).show()
            }else{
                val namaPengeluaran = _binding.edtExpenditureName.text.toString().trim()
                val danaPengeluaran = _binding.edtExpenditurePrice.text.toString().trim()
                val idAlokasi = getIdAlocationSelected!!
                val idUser = sharedPref.getId()
                viewModel.addPengeluaran(idUser, idAlokasi, danaPengeluaran.toInt(), namaPengeluaran).observe(this, { response ->
                    Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
                    if (response.status.equals("success")) {
                        //val intent = Intent(applicationContext, ExpenditureFragment::class.java)
                        //startActivity(intent)
                        finish()
                    }
                })
                Log.d("InputExpenditure", "onclick button: alokasi yang dipilih: nama alokasi = $getStringFromDropdown \n" +
                        "idAlokasi = $getIdAlocationSelected \n danaPengeluaran = ${danaPengeluaran.toInt()}\n namaPengeluaran = $namaPengeluaran")
            }

        }

    }

    fun addItemsOnSpinner(mListNameAlocation: ArrayList<String>, mListIdAlocation: ArrayList<Int>) {

        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, mListNameAlocation
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding.spinnerAlocationType.setAdapter(dataAdapter)

        _binding.spinnerAlocationType.setPrompt("Pilih Tipe Alokasi")

        _binding.spinnerAlocationType.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                number = position
                getStringFromDropdown = mListNameAlocation[position]
                getIdAlocationSelected = mListIdAlocation[position]
                Log.d("InputExpenditure", "onItemSelected: alokasi yang dipilih: nama alokasi = $getStringFromDropdown \n" +
                        "idAlokasi = $getIdAlocationSelected \n number = $number")
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                _binding.spinnerAlocationType
            }

        })

    }
}