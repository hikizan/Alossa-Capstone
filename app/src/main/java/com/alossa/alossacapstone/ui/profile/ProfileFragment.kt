package com.alossa.alossacapstone.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alossa.alossacapstone.databinding.FragmentProfileBinding
import com.alossa.alossacapstone.ui.auth.LoginActivity
import com.alossa.alossacapstone.utils.SharedPref

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferences = SharedPref(binding.root.context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.borderMonthReport.setOnClickListener {
            val moveToListMonth = Intent(requireContext(), MonthlyReportActivity::class.java)
            startActivity(moveToListMonth)
        }

        binding.borderWishlist.setOnClickListener {
            val moveToWishlist = Intent(binding.root.context, WishlistActivity::class.java)
            startActivity(moveToWishlist)
        }
        binding.btnLogout.setOnClickListener {
            sharedPreferences.logout()
            val moveToLogin = Intent(requireContext(), LoginActivity::class.java)
            startActivity(moveToLogin)
        }
    }

}