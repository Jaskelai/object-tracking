package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.BottomSheetPhotoChooserBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseBottomSheet
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class PhotoChooserBottomSheet : BaseBottomSheet<BottomSheetPhotoChooserBinding, PhotoChooserViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val viewModel: PhotoChooserViewModel by viewModels { viewModelFactory }
    override fun getLayoutResId(): Int = R.layout.bottom_sheet_photo_chooser

    override fun onCreate(savedInstanceState: Bundle?) {
        getMainFlowSubcomponent().photoChooserSubcomponent()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
}