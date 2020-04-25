package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.FragmentNewItemBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class NewItemFragment : BaseFragment<FragmentNewItemBinding, NewItemViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: NewItemViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.fragment_new_item

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainFlowSubcomponent().newItemSubcomponent()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun init() {
        observeNavigationResult<Uri>(viewModel.reqCodePhoto.toString())
    }
}