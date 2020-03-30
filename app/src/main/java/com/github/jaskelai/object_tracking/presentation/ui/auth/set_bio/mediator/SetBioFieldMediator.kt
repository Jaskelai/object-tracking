package com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio.mediator

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelLifecycleOwner
import javax.inject.Inject

class SetBioFieldMediator @Inject constructor(

    viewModelLifecycleOwner: ViewModelLifecycleOwner

): LifecycleOwner by viewModelLifecycleOwner   {

    val name = MutableLiveData("")
    val surname = MutableLiveData("")
}
