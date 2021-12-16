package com.alossa.alossacapstone.ui.home

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.R
import com.alossa.alossacapstone.adapter.AlokasiAdapter
import com.alossa.alossacapstone.databinding.ActivityAddExpenditureBinding
import com.alossa.alossacapstone.utils.SharedPref
import com.alossa.alossacapstone.utils.ViewModelFactory

class AddExpenditureActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var sharedPref: SharedPref
    private lateinit var alokasiAdapter: AlokasiAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var binding: ActivityAddExpenditureBinding
    var totalAlokasi = 0
    var sisaDana = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        val pemasukan = binding.edtxPemasukan.text

        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedPref = SharedPref(this)
        alokasiAdapter = AlokasiAdapter()
        recycler = binding.rvAlokasi

        initAction()

        viewModel.getWihsListByIdUser(sharedPref.getId()).observe(this, { wishlist ->
            for (listWishlist in wishlist) {
                if (listWishlist.status == 1) {
                    totalAlokasi += listWishlist.targetDana
                }
            }
            binding.txtDanaAlokasi.text = totalAlokasi.toString()
        })

        viewModel.getAlokasiByIdUser(sharedPref.getId()).observe(this, {
            alokasiAdapter.setAlokasi(it)
        })

        binding.btnCheck.setOnClickListener {
            if (pemasukan.toString().isNotEmpty()) {
                if (pemasukan.toString().toLong() < totalAlokasi) {
                    Toast.makeText(
                        this,
                        "Pemasukan lebih kecil dari dana wishlist",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    sisaDana = (pemasukan.toString().toLong() - totalAlokasi).toInt()
                    binding.txtSisaDana.text = "Rp.$sisaDana"
                }
            } else {
                println("Empty")
            }
        }

        with(recycler) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = alokasiAdapter
        }

        binding.btnAddAlokasi.setOnClickListener {
            Dialog(this).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(true)
                setContentView(R.layout.activity_tambah_alokasi)
                val namaAlokasi = this.findViewById<EditText>(R.id.edtx_nama_alokasi).text
                val nominal = this.findViewById<EditText>(R.id.edtx_nominal).text
                val btnSubmit = this.findViewById<Button>(R.id.btn_add_alokasi)
                btnSubmit.setOnClickListener {
                    if (nominal.toString().isNotEmpty()) {
                        if ((sisaDana - nominal.toString().toInt()) >= 0) {
                            sisaDana -= nominal.toString().toInt()
                            binding.txtSisaDana.text = "Rp.$sisaDana"
                            viewModel.addAlokasi(
                                sharedPref.getId(),
                                namaAlokasi.toString(),
                                1,
                                nominal.toString().toInt()
                            )
                                .observe(this@AddExpenditureActivity, { response ->
                                    Toast.makeText(
                                        this@AddExpenditureActivity,
                                        response.msg,
                                        Toast.LENGTH_LONG
                                    ).show()
                                    if (response.status.equals("success")) {
                                        this.dismiss()
                                        viewModel.getAlokasiByIdUser(sharedPref.getId())
                                            .observe(this@AddExpenditureActivity, {
                                                alokasiAdapter.setAlokasi(it)
                                            })
                                    }
                                })
                        } else {
                            Toast.makeText(
                                this@AddExpenditureActivity,
                                "Dana tidak cukup sisa dana anda $sisaDana",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }.show()
        }
    }

    fun initAction() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val alokasi = (viewHolder as AlokasiAdapter.AlokasiViewHolder).getAlokasi
                viewModel.deleteAlokasi(alokasi.id)
                    .observe(this@AddExpenditureActivity, { response ->
                        Toast.makeText( this@AddExpenditureActivity, response.msg, Toast.LENGTH_LONG)
                            .show()
                        if (response.status.equals("success")) {
                            sisaDana += alokasi.nominal
                            binding.txtSisaDana.text = "Rp.$sisaDana"
                            viewModel.getAlokasiByIdUser(sharedPref.getId())
                                .observe(this@AddExpenditureActivity, {
                                    alokasiAdapter.setAlokasi(it)
                                })
                        }
                    })
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }
}