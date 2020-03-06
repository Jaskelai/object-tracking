package com.github.jaskelai.object_tracking.data.mapper

import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthSuccess
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseUserAuthMapper @Inject constructor() {

    fun mapFirebaseUserToUserAuth(firebaseUser: FirebaseUser, isNewUser: Boolean): UserAuthSuccess =
        UserAuthSuccess(
            isNewUser,
            firebaseUser.metadata?.creationTimestamp,
            firebaseUser.metadata?.lastSignInTimestamp,
            firebaseUser.displayName,
            firebaseUser.phoneNumber
        )
}
