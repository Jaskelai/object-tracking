package com.github.jaskelai.object_tracking.presentation.base.adapter

interface DiffAdapter<T> {

    fun updateItems(itemList: List<T>)

    fun add(item: T)
    fun addAll(itemList: List<T>)

    fun addAtPosition(position: Int, item: T)
    fun addAllAtPosition(position: Int, itemList: List<T>)

    fun clearAll()
}