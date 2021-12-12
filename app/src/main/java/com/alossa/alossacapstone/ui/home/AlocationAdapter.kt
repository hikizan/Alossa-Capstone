package com.alossa.alossacapstone.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.data.model.Alokasi
import com.alossa.alossacapstone.databinding.ItemRowAlocationBinding
import java.util.ArrayList

class AlocationAdapter :
    RecyclerView.Adapter<AlocationAdapter.ViewHolder>() {

    private var listAlocation = ArrayList<Alokasi>()

    //var listTypeAlocation = ArrayList<Alokasi>()
    //var listNominalAlcation = ArrayList<Alokasi>()
    var listNominalAlcation: Array<Int> = arrayOf()
    var listTypeAlocation: Array<String> = arrayOf()


    fun setAlocation(alocations: List<Alokasi>){
        if (alocations == null) return
        this.listAlocation.clear()
        this.listAlocation.addAll(alocations)

        for (alokasiItem in listAlocation){

            /*
            val alokasiType = Alokasi(
                namaAlokasi = alokasiItem.namaAlokasi
            )
            listTypeAlocation.add(alokasiType)

            val alokasiNominal = Alokasi(
                nominal = alokasiItem.nominal
            )

            listNominalAlcation.add(alokasiNominal)
             */
            //listNominalAlcation += alokasiItem.nominal?.toInt() ?: 0
            //listTypeAlocation += alokasiItem.namaAlokasi.toString()
        }
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
        listTypeAlocation += alocation.namaAlokasi.toString()
        listNominalAlcation += alocation.nominal?.toInt() ?: 0
        Log.d("AlocationAdapter", "onBindViewHolder: listTypeAlocation = $listTypeAlocation")
    }

    override fun getItemCount(): Int = listAlocation.size

}