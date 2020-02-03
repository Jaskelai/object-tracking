package com.github.jaskelai.object_tracking.presentation.ui.start_screen

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.getMainActivity
import javax.inject.Inject

class StartScreenFragment : BaseFragment(R.layout.fragment_start_screen) {

    companion object {

        fun getInstance() = StartScreenFragment()
    }

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
}
