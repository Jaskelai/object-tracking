package com.github.jaskelai.object_tracking.domain.model.user_auth

data class UserAuthSuccess(

    val isNewUser: Boolean,

    val createdAt: Long?,
    val lastSignIn: Long?,

    val name: String?,
    val phoneNumber: String?
)
