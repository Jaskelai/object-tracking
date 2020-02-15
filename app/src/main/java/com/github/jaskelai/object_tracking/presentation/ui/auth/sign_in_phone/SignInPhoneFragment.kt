package com.github.jaskelai.object_tracking.presentation.ui.auth.sign_in_phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentSignInPhoneBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.bindData
import com.github.jaskelai.object_tracking.presentation.utils.ext.getMainActivity
import javax.inject.Inject

class SignInPhoneFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val signInPhoneViewModel: SignInPhoneViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .signInSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindData<FragmentSignInPhoneBinding>(
            inflater,
            container,
            R.layout.fragment_sign_in_phone
        ).apply {
            viewModel = signInPhoneViewModel
        }.root
    }

    override fun observeNavigation() {
        signInPhoneViewModel.backNavigationLiveData.observe(this) {
            if (it) findNavController().popBackStack()
        }
    }
}