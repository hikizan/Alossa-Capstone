package com.alossa.alossacapstone.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.databinding.ItemRowAlocationBinding
import java.util.*

class AlocationAdapter :
    RecyclerView.Adapter<AlocationAdapter.ViewHolder>() {

    private var listAlocation = ArrayList<Alokasi>()

    fun setAlocation(alocations: List<Alokasi>){
        if (alocations == null) return
        this.listAlocation.clear()
        this.listAlocation.addAll(alocations)

    }

    class ViewHolder(var binding: ItemRowAlocationBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(alocation: Alokasi){
            with(binding) {
                tvAlocationTypeName.text = alocation.namaAlokasi
                tvAlocationNominal.text = alocation.nominal.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowAlocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alocation = listAlocation[position]
        holder.bind(alocation)
    }

    override fun getItemCount(): Int = listAlocation.size

}