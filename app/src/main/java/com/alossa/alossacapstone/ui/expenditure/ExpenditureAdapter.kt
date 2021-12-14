package com.alossa.alossacapstone.ui.expenditure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.data.model.Pengeluaran
import com.alossa.alossacapstone.databinding.ItemRowExpenditureBinding

class ExpenditureAdapter : RecyclerView.Adapter<ExpenditureAdapter.ViewHolder>(){

    private var listExpenditure = ArrayList<Pengeluaran>()

    fun setExpenditures(expenditures: List<Pengeluaran>){
        if (expenditures == null) return
        this.listExpenditure.clear()
        this.listExpenditure.addAll(expenditures)
    }

    class ViewHolder(var binding: ItemRowExpenditureBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(expenditure: Pengeluaran){
            with(binding) {
                tvExpenditureName.text = expenditure.namaPengeluaran
                tvExpenditurePrice.text = expenditure.danaPengeluaran.toString()
                tvExpenditureDate.text = expenditure.createdAt
                tvExpenditureType.text = expenditure.namaAlokasi
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowExpenditureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expenditure = listExpenditure[position]
        holder.bind(expenditure)
    }

    override fun getItemCount(): Int = listExpenditure.size
}