package com.github.jaskelai.object_tracking.presentation.base.adapter

interface DiffListItemModel {

    fun isSameAs(model: DiffListItemModel): Boolean {

        return model == this
    }
}