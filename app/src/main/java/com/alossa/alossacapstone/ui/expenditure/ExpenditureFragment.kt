package com.alossa.alossacapstone.ui.expenditure

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.databinding.FragmentExpenditureBinding

class ExpenditureFragment : Fragment() {

    private var _binding: FragmentExpenditureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpenditureBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddExpenditure.setOnClickListener {
            val moveToInput = Intent(requireContext(), InputExpenditureActivity::class.java)
            startActivity(moveToInput)
        }
    }

}