package com.alossa.alossacapstone.ui.expenditure

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityInputExpenditureBinding
import com.alossa.alossacapstone.utils.ViewModelFactory


class InputExpenditureActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputExpenditureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputExpenditureBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]


        addItemsOnSpinner()

        val getFromDropDown = _binding.spinnerAlocationType.selectedItem.toString()
        Log.d("InputExpenditure", "onCreate: getFromDropDown = $getFromDropDown")


        _binding.btnSubmitExpenditure.setOnClickListener {
            /*
            val namaPengeluaran = _binding.edtExpenditureName
            val danaAlokasi = _binding.edtExpenditurePrice
            val idUser = 2 //masih hardcode

             */
            Log.d("InputExpenditure", "fun addItemsOnSpinner: getFromDropDown = $getFromDropDown")
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

    }
}