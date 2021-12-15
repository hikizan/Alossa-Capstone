package com.alossa.alossacapstone.ui.expenditure

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityInputExpenditureBinding
import com.alossa.alossacapstone.utils.ViewModelFactory
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.ui.home.HomeViewModel


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
        setContentView(_binding.root)

        factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]
        viewModelFromHome = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        val namaPengeluaranAlokasi = _binding.edtExpenditureName.text.toString().trim()
        var danaPengeluaranAlokasi = _binding.edtExpenditurePrice.text.toString().trim()

        listIdAlocation.add(0)
        listNameAlocation.add("Pilih Tipe Alokasi...")
        viewModelFromHome.getAlokasiByIdUser(2).observe(this,{ alocations ->
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
                val namaPengeluaran = namaPengeluaranAlokasi
                val danaPengeluaran = danaPengeluaranAlokasi.toInt()
                val idAlokasi = getIdAlocationSelected!!
                val idUser = 2 //masih hardcode
                viewModel.addPengeluaran(idUser, idAlokasi, danaPengeluaran, namaPengeluaran).observe(this, { response ->
                    Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
                    if (response.status.equals("success")) {
                        val intent = Intent(this, ExpenditureFragment::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
            }


            Log.d("InputExpenditure", "onclick button: alokasi yang dipilih: nama alokasi = $getStringFromDropdown \n" +
                    "idAlokasi = $getIdAlocationSelected \n number = $number")
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