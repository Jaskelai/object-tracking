package com.github.jaskelai.object_tracking.presentation.utils.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

typealias Action<T> = (T?) -> Unit

fun <T> LiveData<T>.doAfterNext(onNext: Action<T>): MutableLiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    mutableLiveData.addSource(this) {
        mutableLiveData.value = it
        onNext(it)
    }
    return mutableLiveData
}

fun List<LiveData<Boolean>>.isAllTrue(): LiveData<Boolean>  {
    val isAllValid = MediatorLiveData<Boolean>()
    this.forEach {
        isAllValid.addSource(it) {
            isAllValid.value = this.all { it.value ?: false }
        }
    }

    return isAllValid
}

fun <T, O> LiveData<T>.map(function: (T) -> O): LiveData<O> {
    return Transformations.map(this, function)
}

fun <T> LiveData<T>.skip(count:Int): LiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    var skippedCount = 0
    mutableLiveData.addSource(this) {
        if(skippedCount>=count) {
            mutableLiveData.value = it
        }
        skippedCount++
    }
    return mutableLiveData
}

fun <T> LiveData<T>.startWith(startingValue: T?): LiveData<T> {
    val finalLiveData = MediatorLiveData<T>()
    var startingData: LiveData<T>? = MutableLiveData(startingValue)
    finalLiveData.addSource(this) {
        if (null != startingData) {
            finalLiveData.removeSource(startingData!!)
            startingData = null
        }
        finalLiveData.value = it
    }
    finalLiveData.addSource(startingData!!) {
        finalLiveData.value = it
        finalLiveData.removeSource(startingData!!)
        startingData = null
    }
    return finalLiveData
}
