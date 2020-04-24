package com.github.jaskelai.object_tracking.presentation.ui.main_flow

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentMainFlowBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.getMainActivitySubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.MainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_flow.*
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

    override fun init() {
        val navController = requireActivity().findNavController(R.id.nav_host_main_flow_fragment)
        bottomNav.setupWithNavController(navController)
    }
}

fun Fragment.getMainFlowSubcomponent() = (parentFragment?.parentFragment as MainFlowFragment).mainFlowSubcomponent