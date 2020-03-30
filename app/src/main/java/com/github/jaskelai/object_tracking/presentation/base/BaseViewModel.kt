package com.github.jaskelai.object_tracking.presentation.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {

    val errorMessageLiveData = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<Boolean>()
    val backNavigationLiveData = SingleEventLiveData(false)

    private val superVisorJob = SupervisorJob()

    final override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + superVisorJob + defaultExceptionHandler

    private val defaultExceptionHandler = CoroutineExceptionHandler { _, exception ->

        handleCoroutineException(exception)
    }

    init {
        progressLiveData.value = false
    }

    open fun onBackButtonClicked() {
        backNavigationLiveData.value = true
    }

    @CallSuper
    protected open fun handleCoroutineException(ex: Throwable) { }

    @CallSuper
    override fun onCleared() {
        super.onCleared()

        superVisorJob.cancel()
    }
}
