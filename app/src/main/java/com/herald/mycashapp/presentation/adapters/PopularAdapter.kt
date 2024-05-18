package com.herald.mycashapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.databinding.PopularItemBinding
import com.herald.mycashapp.domain.models.PopularSellersModel

class PopularAdapter(private val itemList: List<PopularSellersModel.Data>) :
    RecyclerView.Adapter<PopularAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size
    class ItemViewHolder(private val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularSellersModel.Data,context: Context = binding.root.context) {
            binding.run {
                txtName.text = item.name
                txtDistance.text = item.distance + " km"
                txtRating.text = item.rate
                rating.rating = item.rate.toFloat()
                btnAddFav.setOnClickListener {
                    btnAddFav.visibility = View.GONE
                    btnRemoveFav.visibility = View.VISIBLE
                }
                btnRemoveFav.setOnClickListener {
                    btnAddFav.visibility = View.VISIBLE
                    btnRemoveFav.visibility = View.GONE
                }
            }
            Glide.with(context)
                .load(item.image)
                .error(ContextCompat.getDrawable(context, R.drawable.ramen))
                .into(binding.imgMeal)
        }
    }

}