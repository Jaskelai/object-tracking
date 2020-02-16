package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.auth_send_sms.PhoneAuthCallback
import com.github.jaskelai.object_tracking.databinding.FragmentAuthPhoneBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.getMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthPhoneFragment : BaseFragment<FragmentAuthPhoneBinding, AuthPhoneViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var phoneAuthCallback: PhoneAuthCallback

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override val viewModel: AuthPhoneViewModel by viewModels { viewModelFactory }

    override fun getLayoutResId(): Int = R.layout.fragment_auth_phone

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .signInSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun observeNavigation() {
        viewModel.backNavigationLiveData.observe(this) {
            if (it) findNavController().popBackStack()
        }
        viewModel.toSmsCodeNavigationLiveData.observe(this) {
        }
    }

    override fun init() {
        viewModel.onSendSmsButtonClickedLiveData.observe(this) {
            if (it) verifyPhoneNumber()
        }
        viewModel.authResultLiveData.observe(this) {}
    }

    private fun verifyPhoneNumber() {
        PhoneAuthProvider.getInstance(firebaseAuth).verifyPhoneNumber(
            viewModel.phoneNumber,
            TIMEOUT_DURATION_SECONDS,
            TimeUnit.SECONDS,
            requireActivity(),
            phoneAuthCallback
        )
    }
}