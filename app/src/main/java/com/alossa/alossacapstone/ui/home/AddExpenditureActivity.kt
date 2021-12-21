package com.alossa.alossacapstone.ui.home

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddExpenditureActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var factory: ViewModelFactory
    private lateinit var sharedPref: SharedPref
    private lateinit var alokasiAdapter: AlokasiAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var binding: ActivityAddExpenditureBinding
    private var pemasukan: String? = null
    var totalAlokasi = 0
    var sisaDana = 0
    var totalDanaAlokasi  = 0

    private val listIdIncome = ArrayList<Int>()
    var getIdIncomesCreated: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenditureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Input Pemasukan & Alokasi"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        factory = ViewModelFactory.getInstance()
        pemasukan = binding.edtxPemasukan.text.toString()
        val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        val thisMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1

        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        sharedPref = SharedPref(this)
        alokasiAdapter = AlokasiAdapter()
        recycler = binding.rvAlokasi

        initAction()

        viewModel.getAlokasiByIdUser(sharedPref.getId()).observe(this, {
            alokasiAdapter.setAlokasi(it)
            for (itemAlokasi in it){
                totalDanaAlokasi += itemAlokasi.nominal
            }
        })

        viewModel.getWihsListByIdUser(sharedPref.getId()).observe(this, { wishlist ->
            for (listWishlist in wishlist) {
                if (listWishlist.status == 1) {
                    totalAlokasi += (listWishlist.targetDana/listWishlist.durasi)
                }
            }
            binding.txtDanaAlokasi.text = totalAlokasi.toString()

            viewModel.getPemasukanByIdUser(sharedPref.getId()).observe(this, { pemasukan ->
                for (itemPemasukan in pemasukan) {
                    getIdIncomesCreated = itemPemasukan.id
                    val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val dt = format1.parse(itemPemasukan.createdAt.toString())

                    val month = DateFormat.format("MM", dt) as String
                    val year = DateFormat.format("yyyy", dt) as String

                    if (year == thisYear.toString() && month == thisMonth.toString() && itemPemasukan.status == 0) {
                        sisaDana = (itemPemasukan.danaPemasukan -  totalAlokasi - totalDanaAlokasi)
                        binding.txtSisaDana.text = "Rp.$sisaDana"
                        binding.edtxPemasukan.setText(itemPemasukan.danaPemasukan.toString())

                        binding.edtxPemasukan.setTextColor(Color.GRAY)
                        binding.edtxPemasukan.inputType = InputType.TYPE_NULL
                        binding.btnCheck.setBackgroundResource(R.drawable.red_outline)
                        binding.btnCheck.isEnabled = false
                        binding.btnAddAlokasi.visibility = View.VISIBLE
                        binding.btnSubmit.visibility = View.VISIBLE
                    }
                }
            })
        })


        binding.btnCheck.setOnClickListener {
            pemasukan = binding.edtxPemasukan.text.toString()
            if (pemasukan.isNullOrEmpty()) {
                Toast.makeText(this, "Input field Pemasukan dulu ya", Toast.LENGTH_SHORT).show()
                println("Empty")
                Log.d("AddExpenditure", "onClick: btnCheck: pemasukan = $pemasukan ")

            } else {
                Log.d(
                    "AddExpenditure",
                    "onClick: btnCheck: from else branch pemasukan = $pemasukan "
                )
                if (pemasukan!!.toLong() < totalAlokasi) {
                    Toast.makeText(
                        this,
                        "Pemasukan lebih kecil dari dana wishlist",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    sisaDana = (pemasukan!!.toLong() - totalAlokasi).toInt()
                    binding.txtSisaDana.text = "Rp.$sisaDana"

                    onCheckedButton()

                }
            }
        }

        with(recycler) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = alokasiAdapter
        }


        binding.btnAddAlokasi.setOnClickListener {
            Log.d("AddExpenditure", "onClick: btnAddAlokasi: idPemasukan = $getIdIncomesCreated ")

            if (getIdIncomesCreated != null) {

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
                                    getIdIncomesCreated!!,
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

        binding.btnSubmit.setOnClickListener {
            if (sisaDana == 0) {
                viewModel.updateStatusPemasukan(getIdIncomesCreated!!, sharedPref.getId(), 1)
                    .observe(this, {response->
                        if (response.status.equals("success")){
                            Toast.makeText(this, "Sukses menambah data )", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    })
            } else {
                Toast.makeText(this, "Alokasikan dana yang tersisa :)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onCheckedButton() {
        viewModel.addPemasukan(sharedPref.getId(), pemasukan!!.trim().toInt()).observe(this, {
            if (it.status.equals("success")) {
                Log.d("AddExpenditure", "onClick: btnCheck: nominal pemasukan = ${pemasukan} ")
                binding.edtxPemasukan.setTextColor(Color.GRAY)
                binding.edtxPemasukan.inputType = InputType.TYPE_NULL
                binding.btnCheck.setBackgroundResource(R.drawable.red_outline)
                binding.btnCheck.isEnabled = false
                binding.btnAddAlokasi.visibility = View.VISIBLE
                binding.btnSubmit.visibility = View.VISIBLE

                viewModel.getPemasukanByIdUser(sharedPref.getId()).observe(this, { incomes ->
                    if (incomes.isNotEmpty()) {
                        for (alokasiItem in incomes) {
                            alokasiItem.id?.let { idPemasukan -> listIdIncome.add(idPemasukan) }
                        }

                        for (getLatestPostion in 0..listIdIncome.size) {
                            if (getLatestPostion == (listIdIncome.size - 1)) {
                                getIdIncomesCreated = listIdIncome[getLatestPostion]
                                Log.d(
                                    "AddExpenditure",
                                    "onClick: btnCheck: idPemasukan = ${listIdIncome[getLatestPostion]} \npostion = $getLatestPostion"
                                )
                            }
                        }
                    }
                    Log.d("AddExpenditure", "onClick: btnCheck: listIdIncome = $listIdIncome")
                })
            }
        })
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
                        Toast.makeText(this@AddExpenditureActivity, response.msg, Toast.LENGTH_LONG)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}