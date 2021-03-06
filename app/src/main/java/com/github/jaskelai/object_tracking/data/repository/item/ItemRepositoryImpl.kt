package com.github.jaskelai.object_tracking.data.repository.item

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.mapper.FirebaseDBErrorMapper
import com.github.jaskelai.object_tracking.data.network.di.UserDBFirebaseQualifier
import com.github.jaskelai.object_tracking.data.repository.item.model.ItemNet
import com.github.jaskelai.object_tracking.domain.interfaces.ItemRepository
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.item.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ItemRepositoryImpl @Inject constructor(
    private val firebaseDBErrorMapper: FirebaseDBErrorMapper,
    @UserDBFirebaseQualifier private val userDbReference: DatabaseReference,
    private val auth: FirebaseAuth
) : ItemRepository {

    override suspend fun addItem(item: Item): Result<Unit, ErrorModel> {

        val userId = auth.currentUser?.uid ?: ""

        userDbReference.child(userId).child(userDbReference.push().key ?: "").setValue(item)

        return suspendCoroutine { cont ->

            userDbReference.child(userId).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val item = dataSnapshot.getValue(ItemNet::class.java)
                    item ?: cont.resume(Result.Error(ErrorModel(messageId = R.string.error_common)))
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