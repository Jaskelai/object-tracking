package com.github.jaskelai.object_tracking.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {

    val errorMessageLiveData = MutableLiveData<Int>()
    val progressLiveData = MutableLiveData<Boolean>()

    private val superVisorJob = SupervisorJob()

    final override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superVisorJob + defaultExceptionHandler

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, exception ->

        handleCoroutineException(exception)
    }

    init {
        progressLiveData.value = false
    }

    @CallSuper
    protected open fun handleCoroutineException(ex: Throwable) { }

    @CallSuper
    override fun onCleared() {
        super.onCleared()

        superVisorJob.cancel()
    }
}
