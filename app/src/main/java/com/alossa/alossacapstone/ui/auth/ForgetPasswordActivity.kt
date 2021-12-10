package com.alossa.alossacapstone.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityForgetPasswordBinding
import com.alossa.alossacapstone.utils.ViewModelFactory

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.progressBar.visibility = View.GONE

        binding.btnSendCode.setOnClickListener{
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.forgetEmail.text
            viewModel.forgetPassword(email.toString()).observe(this, {response->
                Toast.makeText( this ,response.msg, Toast.LENGTH_LONG).show()
                if (response.status.equals("success")){
                    val intent = Intent(this, ConfirmPasswordActivity::class.java)
                    intent.putExtra(ConfirmPasswordActivity.EXTRA_EMAIL, email.toString())
                    startActivity(intent)
                    finish()
                }
                binding.progressBar.visibility = View.GONE
            })
        }

        binding.txtResend.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.forgetEmail.text
            viewModel.forgetPassword(email.toString()).observe(this, {response->
                Toast.makeText( this ,response.msg, Toast.LENGTH_LONG).show()
                if (response.status.equals("success")){
                    val intent = Intent(this, ConfirmPasswordActivity::class.java)
                    intent.putExtra(ConfirmPasswordActivity.EXTRA_EMAIL, email.toString())
                    startActivity(intent)
                    finish()
                }
                binding.progressBar.visibility = View.GONE
            })
        }

    }

}