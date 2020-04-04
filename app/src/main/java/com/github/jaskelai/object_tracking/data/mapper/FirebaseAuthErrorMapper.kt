package com.github.jaskelai.object_tracking.data.mapper

import com.github.jaskelai.object_tracking.R
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import javax.inject.Inject

class FirebaseAuthErrorMapper @Inject constructor() {

    fun execute(exception: FirebaseException): Int {
        return when (exception) {
            is FirebaseNetworkException -> R.string.error_network
            is FirebaseTooManyRequestsException -> R.string.error_too_many_requests
            is FirebaseApiNotAvailableException -> R.string.error_server_not_available
            else -> R.string.error_common
        }
    }
}
