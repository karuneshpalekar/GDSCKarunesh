package com.karunesh.gdsckarunesh.business.user

import com.karunesh.gdsckarunesh.model.Users

interface UserDetailRepository {
    suspend fun getUserData(callback:(MutableList<Users>)-> Unit)

    suspend fun blockUser(shopId:String,user : Users , success: (Boolean) -> Unit)
}