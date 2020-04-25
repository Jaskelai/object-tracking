package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentUserItemsBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class UserItemsFragment : BaseFragment<FragmentUserItemsBinding, UserItemsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: UserItemsViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.fragment_user_items

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainFlowSubcomponent().userItemsSubcomponent()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}