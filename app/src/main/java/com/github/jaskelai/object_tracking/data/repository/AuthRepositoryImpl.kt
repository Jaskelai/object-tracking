package com.github.jaskelai.object_tracking.data.repository

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.auth_send_sms.SendSmsResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override val sendSmsStatusLiveData = MutableLiveData<SendSmsResult>()
}
