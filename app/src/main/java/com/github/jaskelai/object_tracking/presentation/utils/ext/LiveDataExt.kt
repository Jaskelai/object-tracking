package com.github.jaskelai.object_tracking.presentation.utils.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

typealias OnNextAction<T> = (T?) -> Unit

fun <T> LiveData<T>.doAfterNext(onNext: OnNextAction<T>): MutableLiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    mutableLiveData.addSource(this) {
        mutableLiveData.value = it
        onNext(it)
    }
    return mutableLiveData
}
