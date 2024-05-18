package com.herald.mycashapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.databinding.TrendingItemBinding
import com.herald.mycashapp.domain.models.TrendingSellersModel

class TrendingAdapter(private val itemList: List<TrendingSellersModel.Data>) :
    RecyclerView.Adapter<TrendingAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = TrendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size
    class ItemViewHolder(private val binding: TrendingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingSellersModel.Data,context: Context = binding.root.context) {
            Glide.with(context)
                .load(item.logo)
                .error(ContextCompat.getDrawable(context, R.drawable.ramen))
                .into(binding.imgBrand)
        }
    }

}