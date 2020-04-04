package com.github.jaskelai.object_tracking.data.mapper

import com.github.jaskelai.object_tracking.R
import com.google.firebase.database.DatabaseError
import javax.inject.Inject

class FirebaseDBErrorMapper @Inject constructor() {

    fun execute(error: DatabaseError): Int = when (error.code) {
        -1 -> R.string.error_db_network_1_datastale
        -2 -> R.string.error_db_network_2_failure
        -3 -> R.string.error_db_network_3_permission_denied
        -4 -> R.string.error_db_network_4_aborted_due_to_network
        -6 -> R.string.error_db_network_6_token_expired
        -7 -> R.string.error_db_network_7_token_invalid
        -8 -> R.string.error_db_network_8_too_many_requests
        -9 -> R.string.error_db_network_9_transaction_overriden
        -10 -> R.string.error_db_network_10_service_unavailable
        -24 -> R.string.error_db_network_24_network_error
        else -> R.string.error_common
    }
}