package com.example.intellifyhomeassignment.UI

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val rootReference: DatabaseReference = FirebaseDatabase.getInstance().reference,
    private val userReference: DatabaseReference = rootReference.child("User")
) {
    suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): Response {
        val response = Response()
        try {
            response.users = userReference.get().await().children.map { snapShot ->
                snapShot.getValue(User::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }
}