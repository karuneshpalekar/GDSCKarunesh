package com.karunesh.gdsckarunesh.business.shopDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunesh.gdsckarunesh.model.Shop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopDetailViewModel @ViewModelInject
constructor(
    private val slotDetailRepository: ShopDetailRepository
) : ViewModel() {

    private val _data: MutableLiveData<MutableList<Shop>> = MutableLiveData()
    val data: LiveData<MutableList<Shop>> get() = _data


    suspend fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            slotDetailRepository.getShopDetailsData() { listOfSlot ->
                _data.postValue(listOfSlot)
            }
        }
    }

}