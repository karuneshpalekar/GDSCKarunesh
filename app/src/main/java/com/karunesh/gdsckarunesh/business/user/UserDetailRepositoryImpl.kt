package com.karunesh.gdsckarunesh.business.user

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.karunesh.gdsckarunesh.model.Users
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDetailRepositoryImpl @Inject
constructor(private val db: FirebaseFirestore) : UserDetailRepository {


    override suspend fun getUserData(callback: (MutableList<Users>) -> Unit) {
        val docRef: CollectionReference = db.collection("Users")


        docRef.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w("FireStore", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (value != null) {
                    val list : MutableList<Users> = mutableListOf()
                for (doc in value){
                    val contact = doc.data.getValue("mobileNumber")
                    val name = doc.data.getValue("name")
                    val password = doc.data.getValue("password")

                    val user = Users(
                        mobileNumber = contact.toString(),
                        name= name.toString(),
                        password = password.toString()
                    )

                    list.add(user)
                    callback(list)
                }
            }
        }

    }

    override suspend fun blockUser(shopId:String,user: Users, success: (Boolean) -> Unit) {
         user.mobileNumber?.let { id ->
             db.collection("Blocked").document(shopId).collection(shopId).document(id).set(user).addOnSuccessListener {
                 success(true)
             }.addOnFailureListener {
                 success(false)
             }
         }
    }

}

