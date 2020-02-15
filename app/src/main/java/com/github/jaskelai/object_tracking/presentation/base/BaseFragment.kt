package com.github.jaskelai.object_tracking.presentation.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeNavigation()
    }

    abstract fun observeNavigation()

    protected fun navigateTo(@IdRes destId: Int) {
        findNavController().navigate(destId)
    }
}
