package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.github.jaskelai.object_tracking.BuildConfig
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.databinding.BottomSheetPhotoChooserBinding
import com.github.jaskelai.object_tracking.presentation.base.BaseBottomSheet
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.getMainFlowSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.generateRandomIntReq
import java.io.IOException
import javax.inject.Inject
import kotlin.random.Random

class PhotoChooserBottomSheet :
    BaseBottomSheet<BottomSheetPhotoChooserBinding, PhotoChooserViewModel>() {

    private companion object {
        val REQ_CODE_CAMERA = generateRandomIntReq()
        val REQ_CODE_GALLERY = generateRandomIntReq()
    }

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

    override fun init() {
        viewModel.onNewPhotoClick.observe(this) {
            if (it) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.resolveActivity(requireActivity().packageManager)?.let {
                    val photoFile = try {
                        viewModel.generatePhotoFile()
                    } catch (ex: IOException) {
                        showErrorDialog(getString(R.string.error_common))
                        null
                    }
                    photoFile?.let {
                        val photoUri = FileProvider.getUriForFile(
                            requireContext(),
                            BuildConfig.APPLICATION_ID + ".provider",
                            photoFile
                        )
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    }
                    startActivityForResult(intent, REQ_CODE_CAMERA)
                }
            }
        }
        viewModel.onChooseFromGalleryClick.observe(this) {
            if (it) {
                val mimeTypes = arrayOf("image/jpeg", "image/png")
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                }
                startActivityForResult(intent, REQ_CODE_GALLERY)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQ_CODE_CAMERA -> {
                        viewModel.onPhotoCaptured(
                            reqCode = PhotoChooserBottomSheetArgs.fromBundle(requireArguments()).reqCode
                        )
                }
                REQ_CODE_GALLERY -> {
                    data?.data?.let {

                        viewModel.onPhotoFromGalleryChoosed(
                            imageUri = it,
                            reqCode = PhotoChooserBottomSheetArgs.fromBundle(requireArguments()).reqCode
                        )
                    }
                }
            }
        }

    }
}