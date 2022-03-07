package com.karunesh.gdsckarunesh.adapter.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.model.Shop

class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>(){

    private var shopList = emptyList<Shop>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_shop_details,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val slot = shopList[position]
        holder.bind(slot)
    }

    override fun getItemCount(): Int = shopList.size


    inner class ShopViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        private val shopName  = itemView.findViewById<TextView>(R.id.recycler_shop_name_txt)
        private val location = itemView.findViewById<TextView>(R.id.recycler_shop_location_txt)

        fun bind(shop : Shop){

            shopName.text = shop.shopName
            location.text = shop.location

        }

    }

    fun setData(list : MutableList<Shop>){
        val diffUtil = ShopDiffUtil(shopList,list)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        shopList = list
        diffResults.dispatchUpdatesTo(this)
    }

}