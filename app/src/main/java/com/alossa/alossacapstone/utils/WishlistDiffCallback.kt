package com.alossa.alossacapstone.utils

import androidx.recyclerview.widget.DiffUtil
import com.alossa.alossacapstone.data.model.WishList

class WishlistDiffCallback (private val mOldWishlist: List<WishList>, private val mNewWishlist: List<WishList>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldWishlist.size
    }

    override fun getNewListSize(): Int {
        return mNewWishlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldWishlist[oldItemPosition].createdAt == mNewWishlist[newItemPosition].createdAt
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAlokasi = mOldWishlist[oldItemPosition]
        val newAlokasi = mNewWishlist[newItemPosition]
        return oldAlokasi.createdAt == newAlokasi.createdAt && oldAlokasi.createdAt == newAlokasi.createdAt
    }
}