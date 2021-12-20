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

    private lateinit var viewModel: ExpenditureViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var sharedPref: SharedPref
    private lateinit var expenditureAdapter: ExpenditureAdapter


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

        factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ExpenditureViewModel::class.java]
        expenditureAdapter = ExpenditureAdapter()
        sharedPref = SharedPref(root.context)

        binding.fabAddExpenditure.setOnClickListener {
            val moveToInput = Intent(requireContext(), InputExpenditureActivity::class.java)
            startActivity(moveToInput)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getPengeluaranByIdUser(sharedPref.getId()).observe(viewLifecycleOwner, { expenditures ->
            if (expenditures.isNotEmpty()){
                expenditureAdapter.setExpenditures(expenditures)
                expenditureAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.INVISIBLE
            }else{
//                binding.progressBar.visibility = View.GONE
            }
        })

        binding.rvExpenditure.layoutManager = LinearLayoutManager(context)
        binding.rvExpenditure.setHasFixedSize(true)
        binding.rvExpenditure.adapter = expenditureAdapter
    }

}