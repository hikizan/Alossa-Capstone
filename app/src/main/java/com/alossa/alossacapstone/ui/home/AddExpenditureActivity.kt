package com.alossa.alossacapstone.ui.home

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.databinding.ActivityAddExpenditureBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

class AddExpenditureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        val sharedPref = SharedPref(this)

        println("idd  "+sharedPref.getId())

        viewModel.getWihsListByIdUser(sharedPref.getId()).observe(this, { wishlist->
            var totalAlokasi = 0
            for (listWishlist in wishlist){
                println("sadasd "+listWishlist.status)
                if (listWishlist.status==1){
                    totalAlokasi += listWishlist.targetDana
                }
            }

            binding.txtDanaAlokasi.text = totalAlokasi.toString()
        })

        binding.btnAddAlokasi.setOnClickListener {
            Dialog(this).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(true)
                setContentView(R.layout.activity_tambah_alokasi)
                val namaAlokasi = this.findViewById<EditText>(R.id.edtx_nama_alokasi).text
                val nominal = this.findViewById<EditText>(R.id.edtx_nominal).text
                val btnSubmit = this.findViewById<Button>(R.id.btn_add_alokasi)
                btnSubmit.setOnClickListener {
//                    viewModel.addAlokasi(sharedPref.getId(), namaAlokasi.toString(),1, nominal.toString().toInt())
//                        .observe(lifecycle, {
//
//                        })

                }
             }.show()
        }
    }
}