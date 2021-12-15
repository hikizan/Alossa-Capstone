package com.alossa.alossacapstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.databinding.ItemRowAlocationBinding
import com.alossa.alossacapstone.utils.AlokasiDiffCallback

class AlokasiAdapter : RecyclerView.Adapter<AlokasiAdapter.AlokasiViewHolder>() {

    private var listAlokasi = ArrayList<Alokasi>()

    fun setAlokasi(listAlokasiAlossa: List<Alokasi>) {
        val diffCallback = AlokasiDiffCallback(this.listAlokasi, listAlokasiAlossa)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listAlokasi.clear()
        this.listAlokasi.addAll(listAlokasiAlossa)
        diffResult.dispatchUpdatesTo(this)
    }

    class AlokasiViewHolder(var binding: ItemRowAlocationBinding) : RecyclerView.ViewHolder(binding.root){
        lateinit var getAlokasi: Alokasi

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlokasiViewHolder {
        val view = ItemRowAlocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlokasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlokasiViewHolder, position: Int) {
       val alokasi = listAlokasi[position]
        holder.getAlokasi = alokasi
        holder.binding.apply {
            tvAlocationNominal.text = alokasi.nominal.toString()
            tvAlocationTypeName.text = alokasi.namaAlokasi.toString()
            }
        }

    override fun getItemCount(): Int = listAlokasi.size
}