package com.karunesh.gdsckarunesh.business.shopDetails

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.karunesh.gdsckarunesh.model.Shop
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ShopDetailRepositoryImpl @Inject
constructor(private val db: FirebaseFirestore) : ShopDetailRepository {
    override suspend fun getShopDetailsData(callback: (MutableList<Shop>) -> Unit) {
        val docRef: CollectionReference = db.collection(SHOP)

        docRef.addSnapshotListener { value, e ->
            if (e != null) {
                Log.w("FireStore", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (value != null) {
                val list : MutableList<Shop> = mutableListOf()
                for (doc in value){
                    val location = doc.data.getValue(LOCATION)
                    val shopName = doc.data.getValue(SHOP_NAME)
                    val shop = Shop(
                        location =  location.toString(),
                        shopName = shopName.toString()
                    )
                    list.add(shop)
                    callback(list)
                }
            }
        }
    }


    companion object{
        const val SHOP = "shop"
        const val LOCATION = "location"
        const val SHOP_NAME = "shopName"
    }

}