package com.karunesh.gdsckarunesh.adapter.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.model.Users

class UserAdapter : RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {

    private var userList = emptyList<Users>()
     var listener : UserListener ?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val slot = userList[position]
        holder.bind(slot)
    }

    override fun getItemCount(): Int = userList.size


    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.recycler_user_name_txt)
        private val contact = itemView.findViewById<TextView>(R.id.recycler_user_contact_txt)
        private val blockUser = itemView.findViewById<Button>(R.id.block_user)

        fun bind(user: Users) {

            name.text = user.name
            contact.text = user.mobileNumber
            blockUser.setOnClickListener {
                listener?.blockUser(user)
            }
        }

    }

    fun setData(list: MutableList<Users>) {
        val diffUtil = UserDiffUtil(userList, list)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        userList = list
        diffResults.dispatchUpdatesTo(this)
    }

}