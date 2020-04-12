package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentAuthSmsBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.getMainActivitySubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class AuthSmsFragment : BaseFragment<FragmentAuthSmsBinding, AuthSmsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: AuthSmsViewModel by viewModels { viewModelFactory }

    override fun getLayoutResId(): Int = R.layout.fragment_auth_sms

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivitySubcomponent().authSmsSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}
