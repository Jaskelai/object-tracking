package com.github.jaskelai.object_tracking.data.network.user

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.di.UserDBFirebaseQualifier
import com.github.jaskelai.object_tracking.data.mapper.FirebaseAuthErrorMapper
import com.github.jaskelai.object_tracking.data.mapper.FirebaseDBErrorMapper
import com.github.jaskelai.object_tracking.data.network.user.model.UserInfoNet
import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkUserDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firebaseDBErrorMapper: FirebaseDBErrorMapper,
    private val firebaseAuthErrorMapper: FirebaseAuthErrorMapper,
    @UserDBFirebaseQualifier private val userDbReference: DatabaseReference
) : NetworkUserDataSource {

    override suspend fun signIn(credential: PhoneAuthCredential): Result<Unit, ErrorModel> =
        suspendCoroutine { cont ->
            auth.signInWithCredential(credential)
                .addOnSuccessListener { cont.resume(Result.Success()) }
                .addOnCanceledListener { cont.resume(Result.Error(ErrorModel(R.string.error_canceled))) }
                .addOnFailureListener {
                    when (it) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            cont.resume(Result.Error(ErrorModel(message = it.localizedMessage)))
                        }
                        else -> {
                            cont.resume(
                                Result.Error(
                                    ErrorModel(firebaseAuthErrorMapper.execute(it as FirebaseException))
                                )
                            )
                        }
                    }
                }
        }

    override suspend fun setBio(userBio: UserBio, userId: String): Result<Unit, ErrorModel> {
        userDbReference.child(userId).setValue(userBio)

        return suspendCoroutine { cont ->

            userDbReference.child(userId).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.value as? UserInfoNet
                    user ?: return
                    cont.resume(Result.Success())
                    userDbReference.child(userId).removeEventListener(this)
                }

                override fun onCancelled(error: DatabaseError) {
                    cont.resume(Result.Error(ErrorModel(firebaseDBErrorMapper.execute(error))))
                    userDbReference.child(userId).removeEventListener(this)
                }
            })
        }
    }
}