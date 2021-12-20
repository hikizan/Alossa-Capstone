package com.alossa.alossacapstone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alossa.alossacapstone.data.model.WishList
import com.alossa.alossacapstone.databinding.ItemRowWishlistBinding
import com.alossa.alossacapstone.ui.profile.ProfilViewModel
import com.alossa.alossacapstone.utils.WishlistDiffCallback
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import android.content.Intent
import android.graphics.Color


class WishlistAdapter : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    private var listWishlist = ArrayList<WishList>()

    fun setWishlist(listWishlistAlossa: List<WishList>) {
        val diffCallback = WishlistDiffCallback(this.listWishlist, listWishlistAlossa)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listWishlist.clear()
        this.listWishlist.addAll(listWishlistAlossa)
        diffResult.dispatchUpdatesTo(this)
    }

    class WishlistViewHolder(var binding: ItemRowWishlistBinding) :
        RecyclerView.ViewHolder(binding.root){
             var status: Int = 0
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view =
            ItemRowWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val wishlist = listWishlist[position]
        holder.binding.apply {
            tvWishlistName.text = wishlist.namaBarang
            when(wishlist.status){
                0 ->{
                    tvStatusPro.text = "Tidak Aktif"
                    tvStatusPro.setTextColor(Color.RED)
                    switchWishlist.isChecked = false
                }
                1 ->{
                    tvStatusPro.text = "Proses"
                    tvStatusPro.setTextColor(Color.GREEN)
                    switchWishlist.isChecked = true
                }
                2 ->{
                    tvStatusPro.text = "Selesai"
                    switchWishlist.isClickable = false
                }
            }
            tvWishlistTarget.text = "Rp.${wishlist.targetDana}"
            tvWishlistDurationBymonth.text = "${wishlist.durasi} bulan"
            tvWishlistNominalCollected.text = "Rp.${wishlist.danaTerkumpul}"
            tvWishlistDate.text = wishlist.createdAt

            switchWishlist.setOnCheckedChangeListener { buttonView, isChecked ->
                val wishlistId: Int = wishlist.id.toInt()
                val intent = Intent("custom-message")
                var status = 0
                intent.putExtra("id", wishlistId)
                if (isChecked)  {
                    holder.status = 1
                    status = 1
                    tvStatusPro.text = "Proses"
                    tvStatusPro.setTextColor(Color.GREEN)
                } else {
                    holder.status = 0
                    tvStatusPro.text = "Tidak Aktif"
                    tvStatusPro.setTextColor(Color.RED)
                    status =0
                }
                intent.putExtra("status", status)
                LocalBroadcastManager.getInstance(holder.binding.root.context).sendBroadcast(intent)

            }
        }
    }
    override fun getItemCount(): Int = listWishlist.size
}