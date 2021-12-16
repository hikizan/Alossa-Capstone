package com.alossa.alossacapstone.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.adapter.WishlistAdapter
import com.alossa.alossacapstone.databinding.ActivityWishlistBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

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

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ProfilViewModel::class.java]
        val sharedPref = SharedPref(this)
        val wishlistAdapter = WishlistAdapter()

        viewModel.getWishlistById(sharedPref.getId()).observe(this, {
            wishlistAdapter.setWishlist(it)
        })

        with(_binding.rvMonthReport){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = wishlistAdapter
        }
    }
}