package com.alossa.alossacapstone.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityRegisterBinding
import com.alossa.alossacapstone.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.progressBar.visibility = View.GONE

        binding.btnRegister.setOnClickListener {

            binding.progressBar.visibility = View.VISIBLE
            val name = binding.registerName.text
            val email = binding.registerEmail.text
            val password = binding.registerPassword.text
            val cPassword = binding.registerCPassword.text

            viewModel.register(
                email.toString(),
                name.toString(),
                password.toString(),
                cPassword.toString()
            ).observe(this, { response ->
                Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
                if (response.status.equals("success")) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                binding.progressBar.visibility = View.GONE
            })
        }

        binding.txtLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}