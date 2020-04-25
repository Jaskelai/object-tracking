package com.github.jaskelai.object_tracking.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.github.jaskelai.object_tracking.BR
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<BINDING : ViewDataBinding, VIEWMODEL : BaseViewModel> :
    BottomSheetDialogFragment() {

    protected abstract val viewModel: VIEWMODEL
    protected lateinit var binding: BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeNavigation()
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val layout =
                (it as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            layout?.let { it1 ->
                BottomSheetBehavior.from(it1).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        doDataBinding()
        observeError()
        init()
    }

    private fun doDataBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    protected open fun init() {}

    private fun observeNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(
                    command.directions,
                    getExtras()
                )
                is NavigationCommand.Back -> findNavController().navigateUp()
                is NavigationCommand.BackWithResult -> {
                    findNavController().run {
                        previousBackStackEntry?.savedStateHandle?.set(
                            command.reqCode.toString(),
                            command.result
                        )
                        popBackStack()
                    }
                }
            }
        }
    }

    protected fun <T> setNavigationResult(key: String) {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(viewLifecycleOwner) {
            }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    private fun observeError() {
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            showErrorDialog(it)
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
