package com.github.jaskelai.object_tracking.domain.model.auth_send_sms

sealed class SendSmsResult {

    object Success : SendSmsResult()

    class Error(val error: SendSmsError) : SendSmsResult()

    object Timeout : SendSmsResult()

    object CodeSent : SendSmsResult()
}
