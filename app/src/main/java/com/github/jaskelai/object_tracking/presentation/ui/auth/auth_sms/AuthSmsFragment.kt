package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentAuthSmsBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.getMainActivity
import javax.inject.Inject

class AuthSmsFragment : BaseFragment<FragmentAuthSmsBinding, AuthSmsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: AuthSmsViewModel by viewModels { viewModelFactory }

    override fun getLayoutResId(): Int = R.layout.fragment_auth_sms

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .authSmsSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}
