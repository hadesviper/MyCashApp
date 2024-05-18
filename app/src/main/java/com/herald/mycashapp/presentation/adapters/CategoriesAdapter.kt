package com.herald.mycashapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.databinding.CategoryItemBinding
import com.herald.mycashapp.domain.models.CategoriesModel

class CategoriesAdapter(private val itemList: List<CategoriesModel.Data>) :
    RecyclerView.Adapter<CategoriesAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size
    class ItemViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoriesModel.Data,context: Context = binding.root.context) {
            Glide.with(context)
                .load(item.image)
                .error(ContextCompat.getDrawable(context, R.drawable.ramen))
                .into(binding.imgCategory)
            binding.txtCategory.text = item.nameEn
        }
    }

}