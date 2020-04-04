package com.github.jaskelai.object_tracking.presentation.ui.main_flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentMainFlowBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.getMainActivitySubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.MainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class MainFlowFragment : BaseFragment<FragmentMainFlowBinding, MainFlowViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: MainFlowViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.fragment_main_flow

    lateinit var mainFlowSubcomponent: MainFlowSubcomponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainFlowSubcomponent = getMainActivitySubcomponent().mainFlowSubcomponentBuilder()
            .build()
        mainFlowSubcomponent.inject(this)

        super.onCreate(savedInstanceState)
    }
}

fun Fragment.getMainFlowSubcomponent() = (parentFragment as MainFlowFragment).mainFlowSubcomponent
