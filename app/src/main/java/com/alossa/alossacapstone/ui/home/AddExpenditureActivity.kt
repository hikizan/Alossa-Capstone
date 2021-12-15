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
    private lateinit var sharedPref : SharedPref
    private lateinit var alokasiAdapter : AlokasiAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedPref = SharedPref(this)
        alokasiAdapter = AlokasiAdapter()
        recycler = binding.rvAlokasi

        initAction()

        viewModel.getWihsListByIdUser(sharedPref.getId()).observe(this, { wishlist->
            var totalAlokasi = 0
            for (listWishlist in wishlist){
                if (listWishlist.status==1){
                    totalAlokasi += listWishlist.targetDana
                }
            }
            binding.txtDanaAlokasi.text = totalAlokasi.toString()
        })

        viewModel.getAlokasiByIdUser(sharedPref.getId()).observe(this, {
            alokasiAdapter.setAlokasi(it)
        })

        with(recycler){
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
                    viewModel.addAlokasi(sharedPref.getId(), namaAlokasi.toString(),1, nominal.toString().toInt())
                        .observe(this@AddExpenditureActivity , {response->
                            Toast.makeText(this@AddExpenditureActivity, response.msg, Toast.LENGTH_LONG).show()
                            if (response.status.equals("success")){
                                this.dismiss()
                                viewModel.getAlokasiByIdUser(sharedPref.getId()).observe(this@AddExpenditureActivity, {
                                    alokasiAdapter.setAlokasi(it)
                                })
                            }
                        })
                }
             }.show()
        }
    }

    fun initAction(){
        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.Callback(){
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
                println("sadsa "+alokasi.id)
                viewModel.deleteAlokasi(alokasi.id).observe(this@AddExpenditureActivity, {response->
                    Toast.makeText(this@AddExpenditureActivity, response.msg, Toast.LENGTH_LONG).show()
                    if (response.status.equals("success")){
                        viewModel.getAlokasiByIdUser(sharedPref.getId()).observe(this@AddExpenditureActivity, {
                            alokasiAdapter.setAlokasi(it)
                        })
                    }
                })
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }
}