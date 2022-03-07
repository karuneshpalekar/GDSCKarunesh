package com.karunesh.gdsckarunesh.business.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karunesh.gdsckarunesh.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserDetailViewModel @ViewModelInject
constructor(
    private val userDetailRepository: UserDetailRepository
) : ViewModel() {

    private val _data: MutableLiveData<MutableList<Users>> = MutableLiveData()
    val data: LiveData<MutableList<Users>> get() = _data

    private val _blockValue : MutableLiveData<Boolean> = MutableLiveData()
    val blockValue : LiveData<Boolean> get() = _blockValue

    suspend fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            userDetailRepository.getUserData() { listOfUsers ->
                _data.postValue(listOfUsers)
            }
        }
    }

    suspend fun blockUser(shopId:String,user : Users ){
        viewModelScope.launch(Dispatchers.IO) {
            userDetailRepository.blockUser(shopId,user){
                _blockValue.postValue(it)
            }
        }
    }

}