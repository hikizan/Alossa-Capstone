package com.alossa.alossacapstone.ui.expenditure

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.alossa.alossacapstone.databinding.ActivityInputExpenditureBinding


class InputExpenditureActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputExpenditureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputExpenditureBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        addItemsOnSpinner()

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