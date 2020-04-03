package com.github.jaskelai.object_tracking.presentation.ui.auth.start_screen

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentStartScreenBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.getMainActivity
import javax.inject.Inject

class StartScreenFragment : BaseFragment<FragmentStartScreenBinding, StartScreenViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: StartScreenViewModel by viewModels { viewModelFactory }

    override fun getLayoutResId(): Int = R.layout.fragment_start_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .startScreenSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}
