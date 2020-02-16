package com.github.jaskelai.object_tracking.domain.model.auth_send_sms

import androidx.annotation.StringRes

data class SendSmsError(
    @StringRes val messageId: Int
)
