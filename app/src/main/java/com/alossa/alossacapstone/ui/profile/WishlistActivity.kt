package com.alossa.alossacapstone.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alossa.alossacapstone.databinding.ActivityWishlistBinding

class WishlistActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.fabAddWishlist.setOnClickListener {
            val moveToInputWishlist = Intent(this, InputWishlistActivity::class.java)
            startActivity(moveToInputWishlist)
        }
    }
}