package com.alossa.alossacapstone.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.MainActivity
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.data.model.ResponseServe
import com.alossa.alossacapstone.databinding.ActivityLoginBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        supportActionBar?.hide()

        binding.progressBar.visibility = View.GONE

        sharedPreferences = SharedPref(this)
        if (sharedPreferences.getStatusLogin()) {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.loginEmail.text
            val password = binding.loginPassword.text
            viewModel.login(email.toString(), password.toString()).observe(this, { response ->
                Toast.makeText(this, response.msg, Toast.LENGTH_LONG).show()
                if (response.status.equals("success")) {
                    setLogin(response)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                binding.progressBar.visibility = View.GONE
            })
        }

        binding.textRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.textForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

    }

    fun setLogin(user: ResponseServe) {
        sharedPreferences.setStatusLogin(true)
        sharedPreferences.setId(user.id)
        sharedPreferences.setEmail(user.email)
        sharedPreferences.setName(user.name)
    }
}