package com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentItemDetailsBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class ItemDetailsFragment : BaseFragment<FragmentItemDetailsBinding, ItemDetailsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: ItemDetailsViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.fragment_item_details

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainFlowSubcomponent().itemDetailsSubcomponent()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun init() {
        viewModel.receiveArgs(ItemDetailsFragmentArgs.fromBundle(requireArguments()))
    }
}