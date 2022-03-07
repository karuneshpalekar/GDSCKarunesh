package com.karunesh.gdsckarunesh.business.slot

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.karunesh.gdsckarunesh.model.Slot
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SlotsRepositoryImpl @Inject
constructor(private val db: FirebaseFirestore) : SlotsRepository {

    override suspend fun addSlotsToDatabase(slot: Slot, success: (Boolean) -> Unit) {
        db.collection(SLOTS).document().set(slot).addOnSuccessListener {
            success(true)
        }.addOnFailureListener {
            success(false)
        }

    }

    override suspend fun getData(callback : (MutableList<Slot>)-> Unit) {

        val docRef: CollectionReference = db.collection(SLOTS)

        docRef.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w("FireStore", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (value != null) {

                val list : MutableList<Slot> = mutableListOf()
                for (doc in value) {

                    val shopId = UUID.randomUUID()
                    val slotId = doc.data.getValue(SLOT_ID)
                    val slotDate = doc.data.getValue(SLOT_DATE)
                    val slotStartTime = doc.data.getValue(SLOT_START_TIME)
                    val slotEndTime = doc.data.getValue(SLOT_END_TIME)

                    val slot = Slot(
                        shopId.toString(),
                        slotId.toString(),
                        slotDate.toString(),
                        slotStartTime.toString(),
                        slotEndTime.toString()
                    )
                    list.add(slot)
                    callback(list)
                }

            }
        }


    }

    companion object{
        const val SLOTS = "Slots"
        const val SHOP_ID = "shopId"
        const val SLOT_ID = "slotId"
        const val SLOT_DATE="slotDate"
        const val SLOT_START_TIME ="slotStartTime"
        const val SLOT_END_TIME = "slotEndTime"
    }
}

