package com.alossa.alossacapstone.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.databinding.ItemRowAlocationBinding

class AlocationAdapter(private val listAlocation: List<Alokasi>) :
    RecyclerView.Adapter<AlocationAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemRowAlocationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowAlocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvAlocationTypeName.text = listAlocation[position].namaAlokasi
        holder.binding.tvAlocationNominal.text = listAlocation[position].nominal.toString()
    }

    override fun getItemCount(): Int = listAlocation.size

}