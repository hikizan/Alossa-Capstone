package com.alossa.alossacapstone.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.databinding.ActivityInputWishlistBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

class InputWishlistActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInputWishlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInputWishlistBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        supportActionBar?.title = "Tambah Wishlist"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ProfilViewModel::class.java]
        val sharedPref = SharedPref(this)

        _binding.apply {
            btnSubmitWishlist.setOnClickListener {
                val namaWishList = edtWishlistName.text
                val targetDana = edtWishlistTarget.text
                val durasi = edtWishlistDuration.text

                viewModel.addWishList(sharedPref.getId(), namaWishList.toString(), 2, targetDana.toString().toInt(), durasi.toString().toInt(), 1)
                    .observe(this@InputWishlistActivity, { response->
                        Toast.makeText(
                            this@InputWishlistActivity,
                            response.msg,
                            Toast.LENGTH_LONG
                        ).show()
                        if (response.status.equals("success")) {
                           //val intent = Intent(this@InputWishlistActivity, WishlistActivity::class.java)
                            //startActivity(intent)
                            finish()
                        }
                    })
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}