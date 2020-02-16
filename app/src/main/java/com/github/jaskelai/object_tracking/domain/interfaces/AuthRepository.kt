package com.github.jaskelai.object_tracking.domain.interfaces

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.domain.model.auth_send_sms.SendSmsResult

interface AuthRepository {

    val sendSmsStatusLiveData: MutableLiveData<SendSmsResult>
}
