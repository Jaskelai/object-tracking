package com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio

import com.github.jaskelai.object_tracking.domain.interactor.SetBioInteractor
import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio.mediator.SetBioFieldMediator
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetBioViewModel @Inject constructor(

    val setBioFieldMediator: SetBioFieldMediator,
    private val setBioInteractor: SetBioInteractor,
    private val resourceProvider: ResourceProvider

) : BaseViewModel() {

    fun onRegisterClicked() = launch {

        val result = setBioInteractor.setBio(
            UserBio(
                name = setBioFieldMediator.name.value ?: "",
                surname = setBioFieldMediator.surname.value ?: ""
            )
        )

        handleResult(result)
    }

    private fun handleResult(result: Result<Unit, ErrorModel>) {
        when (result) {
            is Result.Success -> {
                navigate(SetBioFragmentDirections.actionSetBioFragmentToMainFlowFragment())
            }
            is Result.Error -> {
                if (result.data?.message != null) {
                    errorMessageLiveData.value = result.data.message
                }
                if (result.data?.messageId != null)
                    errorMessageLiveData.value =
                        resourceProvider.getString(result.data.messageId)
            }
        }
    }
}
