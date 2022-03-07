package com.karunesh.gdsckarunesh.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.karunesh.gdsckarunesh.R
import com.karunesh.gdsckarunesh.adapter.shop.ShopAdapter
import com.karunesh.gdsckarunesh.business.shopDetails.ShopDetailViewModel
import com.karunesh.gdsckarunesh.databinding.FragmentShopDetailsBinding
import com.karunesh.gdsckarunesh.model.Shop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopDetailsFragment : Fragment() {


    private lateinit var binding: FragmentShopDetailsBinding
    private val viewModel:ShopDetailViewModel by viewModels()
    private val adapter = ShopAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shop_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerViewShopDetails.adapter = adapter
        lifecycleScope.launch {
            viewModel.getData()
        }

        viewModel.data.observe(viewLifecycleOwner,{list ->
            adapter.setData(list)
        })

    }

}