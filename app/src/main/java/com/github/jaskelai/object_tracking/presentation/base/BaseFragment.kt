package com.github.jaskelai.object_tracking.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.github.jaskelai.object_tracking.BR
import com.github.jaskelai.object_tracking.R

abstract class BaseFragment<BINDING : ViewDataBinding, VIEWMODEL : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VIEWMODEL
    protected lateinit var binding: BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeNavigation()
        init()
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        doDataBinding()
        observeError()
    }

    private fun doDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    protected open fun init() {}

    protected open fun observeNavigation() {}

    private fun observeError() {
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showErrorDialog(requireContext().getString(it))
        }
    }

    protected fun navigateTo(@IdRes destId: Int) {
        findNavController().navigate(destId)
    }

    protected fun back() {
        findNavController().popBackStack()
    }

    private fun showErrorDialog(errorMessage: String) {
        AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.ErrorDialogTheme))
            .setMessage(errorMessage)
            .setPositiveButton(R.string.close_error_alert_dialog) { _, _ -> }
            .create()
            .show()
    }
}
