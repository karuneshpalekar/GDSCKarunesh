package com.karunesh.gdsckarunesh.business.shopDetails

import com.karunesh.gdsckarunesh.model.Shop

interface ShopDetailRepository {
    suspend fun getShopDetailsData(callback : (MutableList<Shop>)-> Unit)
}