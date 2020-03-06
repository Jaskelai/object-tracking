package com.github.jaskelai.object_tracking.presentation.ui.set_bio

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentSetBioBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.getMainActivity
import javax.inject.Inject

class SetBioFragment : BaseFragment<FragmentSetBioBinding, SetBioViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: SetBioViewModel by viewModels { viewModelFactory }

    override fun getLayoutResId(): Int = R.layout.fragment_set_bio

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainActivity().mainSubcomponent
            .setBioSubcomponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun observeNavigation() {

    }
}