package com.alossa.alossacapstone.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alossa.alossacapstone.adapter.WishlistAdapter
import com.alossa.alossacapstone.databinding.ActivityWishlistBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory
import android.widget.Toast


import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class WishlistActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityWishlistBinding
    private lateinit var viewModel : ProfilViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        supportActionBar?.title = "Wishlist"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        _binding.progressBar.visibility = View.VISIBLE

        _binding.fabAddWishlist.setOnClickListener {
            val moveToInputWishlist = Intent(this, InputWishlistActivity::class.java)
            startActivity(moveToInputWishlist)
        }

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ProfilViewModel::class.java]
        val sharedPref = SharedPref(this)
        val wishlistAdapter = WishlistAdapter()

        viewModel.getWishlistById(sharedPref.getId()).observe(this, {
            wishlistAdapter.setWishlist(it)
            _binding.progressBar.visibility = View.GONE
        })
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
             IntentFilter("custom-message")
        )

        with(_binding.rvMonthReport){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = wishlistAdapter
        }

    }

    var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val id = intent.getIntExtra("id", 0)
            val status = intent.getIntExtra("status", 0)
            viewModel.updateStatusWishlist(id, status).observe(this@WishlistActivity, {response->
                if (response.status.equals("success")){
                    Toast.makeText(this@WishlistActivity, "${response.msg}", Toast.LENGTH_SHORT).show()
                }
            })
          }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}