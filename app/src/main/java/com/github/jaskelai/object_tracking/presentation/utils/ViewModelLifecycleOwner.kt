package com.github.jaskelai.object_tracking.presentation.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ViewModelLifecycleOwner @Inject constructor() : LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {

        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun getLifecycle() = lifecycleRegistry

    internal fun onStart() {

        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    internal fun onDestroy() {

        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
}