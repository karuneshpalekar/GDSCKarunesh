package com.karunesh.gdsckarunesh.business.slot

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunesh.gdsckarunesh.business.slot.SlotsRepository
import com.karunesh.gdsckarunesh.model.Slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SlotsViewModel @ViewModelInject
constructor(
    private val slotsRepository: SlotsRepository
) : ViewModel() {

    private val _data : MutableLiveData<MutableList<Slot>> = MutableLiveData()
    val data : LiveData<MutableList<Slot>> get() = _data

    private val _slotStatus : MutableLiveData<Boolean> = MutableLiveData()
    val slotStatus : LiveData<Boolean> get() = _slotStatus

    suspend fun addSlots(slot: Slot) {
        viewModelScope.launch(Dispatchers.IO) {
            slotsRepository.addSlotsToDatabase(slot){ value ->
                _slotStatus.postValue(value)
            }
        }
    }


    suspend fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            slotsRepository.getData() { listOfSlot ->
                _data.postValue(listOfSlot)
            }
        }
    }

}