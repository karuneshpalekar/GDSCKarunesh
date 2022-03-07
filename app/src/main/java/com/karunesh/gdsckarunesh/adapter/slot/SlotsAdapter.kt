package com.karunesh.gdsckarunesh.adapter.slot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.model.Slot

class SlotsAdapter : RecyclerView.Adapter<SlotsAdapter.SlotsViewHolder>(){

    private var slotList = emptyList<Slot>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotsViewHolder {
        return SlotsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_slots,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SlotsViewHolder, position: Int) {
        val slot = slotList[position]
        holder.bind(slot)
    }

    override fun getItemCount(): Int = slotList.size


    inner class SlotsViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        private val slotStartTime  = itemView.findViewById<TextView>(R.id.recycler_slot_start_txt)
        private val slotEndTime = itemView.findViewById<TextView>(R.id.recycler_slot_end_txt)

        fun bind(slot : Slot){

            slotStartTime.text = slot.slotStartTime
            slotEndTime.text = slot.slotEndTime

        }

    }

    fun setData(list : MutableList<Slot>){
        val diffUtil = SlotsDiffUtil(slotList,list)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        slotList = list
        diffResults.dispatchUpdatesTo(this)
    }

}