package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.jaskelai.object_tracking.domain.model.item.Item

class ItemDiffUtilCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(old: Item, new: Item): Boolean = old == new

    override fun areContentsTheSame(old: Item, new: Item): Boolean = old == new
}