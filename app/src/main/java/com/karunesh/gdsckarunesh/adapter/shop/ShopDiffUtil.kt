package com.karunesh.gdsckarunesh.adapter.shop

import androidx.recyclerview.widget.DiffUtil
import com.karunesh.gdsckarunesh.model.Shop


class ShopDiffUtil (
    private val oldList : List<Shop>,
    private val newList : List<Shop>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(oldList[oldItemPosition].shopName){
            newList[newItemPosition].shopName ->{
                true
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].shopName !=  newList[newItemPosition].shopName ->{
                false
            }
            else -> true
        }
    }

}