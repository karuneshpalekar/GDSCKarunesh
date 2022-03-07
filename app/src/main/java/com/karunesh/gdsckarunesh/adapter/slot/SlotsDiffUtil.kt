package com.karunesh.gdsckarunesh.adapter.slot

import androidx.recyclerview.widget.DiffUtil
import com.karunesh.gdsckarunesh.model.Slot

class SlotsDiffUtil (
    private val oldList : List<Slot>,
    private val newList : List<Slot>
        ):DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(oldList[oldItemPosition].slotId){
            newList[newItemPosition].slotId ->{
                true
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].slotId !=  newList[newItemPosition].slotId ->{
                false
            }
            else -> true
        }
    }

}