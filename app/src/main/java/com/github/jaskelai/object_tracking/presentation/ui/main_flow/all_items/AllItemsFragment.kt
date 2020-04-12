package com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentAllItemsBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class AllItemsFragment : BaseFragment<FragmentAllItemsBinding, AllItemsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: AllItemsViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.fragment_all_items

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainFlowSubcomponent().allItemsSubcomponent()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}
