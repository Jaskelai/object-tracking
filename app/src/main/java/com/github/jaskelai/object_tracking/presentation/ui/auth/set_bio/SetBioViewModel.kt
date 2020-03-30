package com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio

import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio.mediator.SetBioFieldMediator
import javax.inject.Inject

class SetBioViewModel @Inject constructor(

    val setBioFieldMediator: SetBioFieldMediator

): BaseViewModel() {

    fun onBackButtonClicked() {

    }
}
