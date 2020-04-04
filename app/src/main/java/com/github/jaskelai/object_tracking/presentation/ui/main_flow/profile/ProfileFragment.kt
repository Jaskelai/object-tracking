package com.github.jaskelai.object_tracking.presentation.ui.main_flow.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentProfileBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: ProfileViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.fragment_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainFlowSubcomponent().profileSubcomponent()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}