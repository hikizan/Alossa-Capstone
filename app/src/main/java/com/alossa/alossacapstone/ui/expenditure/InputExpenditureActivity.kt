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


class InputExpenditureActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputExpenditureBinding

    var number: Int? = null
    var getStringFromDropdown: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputExpenditureBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]

        addItemsOnSpinner()

        //var getFromDropDown = _binding.spinnerAlocationType.selectedItemPosition.toString()
        //Log.d("InputExpenditure", "onCreate: getFromDropDown = $getFromDropDown")


        _binding.btnSubmitExpenditure.setOnClickListener {
            /*
            val namaPengeluaran = _binding.edtExpenditureName
            val danaAlokasi = _binding.edtExpenditurePrice
            val idUser = 2 //masih hardcode

             */
            Log.d("InputExpenditure", "fun addItemsOnSpinner: getFromDropDown = $getStringFromDropdown")
            Log.d("InputExpenditure", "onclick button : posisi yang dipilih = $number")
        }


    }

    fun addItemsOnSpinner() {
        val list: MutableList<String> = ArrayList()
        list.add("Uang Makan")
        list.add("Kebutuhan")
        list.add("Uang Jajan")
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, list
        )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        _binding.spinnerAlocationType.setAdapter(dataAdapter)
        _binding.spinnerAlocationType.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                number = position
                getStringFromDropdown = list[position]
                Log.d("InputExpenditure", "onItemSelected: posisi yang dipilih = $number")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this,"Pilih Tipe Alokasi Terlebih Dahulu",Toast.LENGTH_SHORT)
                Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
            }

        })

    }
}