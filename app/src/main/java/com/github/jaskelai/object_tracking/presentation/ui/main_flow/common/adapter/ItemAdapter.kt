package com.github.jaskelai.object_tracking.presentation.ui.main_flow.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.LiItemBinding
import com.github.jaskelai.object_tracking.domain.model.item.Item

class ItemAdapter (
    private val clickListener: ItemClickListener<Item>
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LiItemBinding>(
            layoutInflater,
            R.layout.li_item,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)
    }

    inner class ItemViewHolder(private val binding: LiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Item, clickListener: ItemClickListener<Item>) {
            binding.item = item
            binding.root.setOnClickListener {
                clickListener.omItemClick(item)
            }
            binding.executePendingBindings()
        }
    }
}
