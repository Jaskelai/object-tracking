package com.github.jaskelai.object_tracking.presentation.ui.sign_in

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.getMainActivity
import javax.inject.Inject

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    companion object {

        fun getInstance() = SignInFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val signInViewModel: SignInViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .signInSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}