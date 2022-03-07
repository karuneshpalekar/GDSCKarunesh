package com.karunesh.gdsckarunesh.business.slot

import com.karunesh.gdsckarunesh.model.Slot

interface SlotsRepository {

    suspend fun addSlotsToDatabase(slot : Slot,success:(Boolean)->Unit)

    suspend fun getData(callback : (MutableList<Slot>)-> Unit)
}