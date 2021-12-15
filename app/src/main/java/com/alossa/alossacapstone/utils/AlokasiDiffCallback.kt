package com.alossa.alossacapstone.utils

import androidx.recyclerview.widget.DiffUtil
import com.alossa.alossacapstone.data.model.Alokasi

class AlokasiDiffCallback (private val mOldAlokasi: List<Alokasi>, private val mNewAlokasi: List<Alokasi>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldAlokasi.size
    }

    override fun getNewListSize(): Int {
        return mNewAlokasi.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldAlokasi[oldItemPosition].createdAt == mNewAlokasi[newItemPosition].createdAt
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAlokasi = mOldAlokasi[oldItemPosition]
        val newAlokasi = mNewAlokasi[newItemPosition]
        return oldAlokasi.createdAt == newAlokasi.createdAt && oldAlokasi.createdAt == newAlokasi.createdAt
    }
}