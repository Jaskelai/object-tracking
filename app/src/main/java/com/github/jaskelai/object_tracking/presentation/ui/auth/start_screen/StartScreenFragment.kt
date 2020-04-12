package com.github.jaskelai.object_tracking.presentation.ui.auth.start_screen

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentStartScreenBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.getMainActivitySubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class StartScreenFragment : BaseFragment<FragmentStartScreenBinding, StartScreenViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: StartScreenViewModel by viewModels { viewModelFactory }

    override fun getLayoutResId(): Int = R.layout.fragment_start_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivitySubcomponent().startScreenSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}
