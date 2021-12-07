package com.alossa.alossacapstone.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alossa.alossacapstone.databinding.ActivityInputWishlistBinding

class InputWishlistActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputWishlistBinding.inflate(layoutInflater)
        setContentView(_binding.root)

    }
}