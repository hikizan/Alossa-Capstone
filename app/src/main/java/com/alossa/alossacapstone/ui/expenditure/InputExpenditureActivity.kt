package com.alossa.alossacapstone.ui.expenditure

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityInputExpenditureBinding
import com.alossa.alossacapstone.utils.ViewModelFactory
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.alossa.alossacapstone.ui.home.HomeViewModel


class InputExpenditureActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputExpenditureBinding

    private lateinit var factory: ViewModelFactory
    private lateinit var viewModelFromHome: HomeViewModel

    val listIdAlocation: MutableList<Int> = ArrayList()
    val listNameAlocation: MutableList<String> = ArrayList()
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

        viewModelFromHome.getAlokasiByIdUser(2).observe(this,{ alocations ->
            if (alocations.isNotEmpty()){
                for (alokasiItem in alocations){
                    listNameAlocation.add(alokasiItem.namaAlokasi.toString())
                    alokasiItem.id?.let { listIdAlocation.add(it.toInt()) }
                }
                Log.d("InputExpenditure", "onObserve: alokasi yang dipilih: nama alokasi = $getStringFromDropdown \n" +
                        "idAlokasi = $getIdAlocationSelected \n number = $number")
            }
        })

        addItemsOnSpinner()


        //var getFromDropDown = _binding.spinnerAlocationType.selectedItemPosition.toString()
        //Log.d("InputExpenditure", "onCreate: getFromDropDown = $getFromDropDown")


        _binding.btnSubmitExpenditure.setOnClickListener {
            /*
            val namaPengeluaran = _binding.edtExpenditureName
            val danaAlokasi = _binding.edtExpenditurePrice
            val idUser = 2 //masih hardcode

             */
            Log.d("InputExpenditure", "onclick button: alokasi yang dipilih: nama alokasi = $getStringFromDropdown \n" +
                    "idAlokasi = $getIdAlocationSelected \n number = $number")
        }


    }

    fun addItemsOnSpinner() {
        /*
        list.add("Uang Makan")
        list.add("Kebutuhan")
        list.add("Uang Jajan")
         */
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listNameAlocation
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding.spinnerAlocationType.setAdapter(dataAdapter)
        _binding.spinnerAlocationType.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                number = position
                getStringFromDropdown = listNameAlocation[position]
                getIdAlocationSelected = listIdAlocation[position]
                Log.d("InputExpenditure", "onItemSelected: alokasi yang dipilih: nama alokasi = $getStringFromDropdown \n" +
                        "idAlokasi = $getIdAlocationSelected \n number = $number")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Toast.makeText(this,"Pilih Tipe Alokasi Terlebih Dahulu",Toast.LENGTH_SHORT)
                //Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
            }

        })

    }
}