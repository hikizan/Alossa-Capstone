package com.alossa.alossacapstone.ui.expenditure

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alossa.alossacapstone.databinding.FragmentExpenditureBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

class ExpenditureFragment : Fragment() {

    private var _binding: FragmentExpenditureBinding? = null
    private val binding get() = _binding!!
    private lateinit var root: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenditureBinding.inflate(inflater, container, false)
        root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]
        val expenditureAdapter = ExpenditureAdapter()
        val sharedPref = SharedPref(root.context)

        viewModel.getPengeluaranByIdUser(sharedPref.getId()).observe(viewLifecycleOwner, { expenditures ->
            if (expenditures.isNotEmpty()){
                expenditureAdapter.setExpenditures(expenditures)
                expenditureAdapter.notifyDataSetChanged()

            }
        })

        binding.rvExpenditure.layoutManager = LinearLayoutManager(context)
        binding.rvExpenditure.setHasFixedSize(true)
        binding.rvExpenditure.adapter = expenditureAdapter

        binding.fabAddExpenditure.setOnClickListener {
            val moveToInput = Intent(requireContext(), InputExpenditureActivity::class.java)
            startActivity(moveToInput)
        }
    }

}