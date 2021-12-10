package com.alossa.alossacapstone.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.MainActivity
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.databinding.ActivityConfirmPasswordBinding
import com.alossa.alossacapstone.databinding.ActivityForgetPasswordBinding
import com.alossa.alossacapstone.utils.ViewModelFactory
import java.sql.SQLOutput

class ConfirmPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityConfirmPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.progressBar.visibility = View.GONE
        val email = intent.getStringExtra(EXTRA_EMAIL)

        binding.btnResetPassword.setOnClickListener{
            val code = binding.resetKode.text
            val password = binding.resetPassword.text
            binding.progressBar.visibility = View.VISIBLE
            viewModel.resetPassword(email.toString(), code.toString(), password.toString())
                .observe(this, {response->
                    Toast.makeText( this ,response.msg, Toast.LENGTH_LONG).show()
                    if (response.status.equals("success")){
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    binding.progressBar.visibility = View.GONE
                })
        }

    }

    companion object{
        var EXTRA_EMAIL = "email"
    }
}