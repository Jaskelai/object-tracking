package com.github.jaskelai.object_tracking.presentation.ui.start_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentStartScreenBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.bindData
import com.github.jaskelai.object_tracking.presentation.utils.ext.getMainActivity
import javax.inject.Inject

class StartScreenFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val startScreenViewModel: StartScreenViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .startScreenSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindData<FragmentStartScreenBinding>(
            inflater,
            container,
            R.layout.fragment_start_screen
        ).apply {
            viewModel = startScreenViewModel
        }.root
    }

    override fun observeNavigation() {
        startScreenViewModel.signInNavigationLiveData.observe(this) {
            if (it) navigateTo(R.id.action_startScreenFragment_to_signInFragment)
        }
    }
}
