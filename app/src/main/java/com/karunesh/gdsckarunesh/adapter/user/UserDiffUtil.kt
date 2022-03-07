package com.karunesh.gdsckarunesh.adapter.user

import androidx.recyclerview.widget.DiffUtil
import com.karunesh.gdsckarunesh.model.Users


class UserDiffUtil (
    private val oldList : List<Users>,
    private val newList : List<Users>
): DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when(oldList[oldItemPosition].mobileNumber){
            newList[newItemPosition].mobileNumber->{
                true
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].mobileNumber !=  newList[newItemPosition].mobileNumber ->{
                false
            }
            else -> true
        }
    }

}