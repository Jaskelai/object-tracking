package com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio

import com.github.jaskelai.object_tracking.domain.interactor.SetBioInteractor
import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio.mediator.SetBioFieldMediator
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetBioViewModel @Inject constructor(

    val setBioFieldMediator: SetBioFieldMediator,

    private val setBioInteractor: SetBioInteractor

) : BaseViewModel() {

    fun onRegisterClicked() = launch {
        setBioInteractor.setBio(
            UserBio(
                name = setBioFieldMediator.name.value ?: "",
                surname = setBioFieldMediator.surname.value ?: ""
            )
        )
    }
}
