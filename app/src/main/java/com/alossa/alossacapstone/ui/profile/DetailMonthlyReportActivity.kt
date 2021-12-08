package com.alossa.alossacapstone.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alossa.alossacapstone.databinding.ActivityDetailMonthlyReportBinding

class DetailMonthlyReportActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailMonthlyReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailMonthlyReportBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}