package com.example.intellifyhomeassignment.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class UserViewModel(private val repository: UserRepository= UserRepository()): ViewModel() {
  val responseLiveData = liveData(Dispatchers.IO) {
    emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
  }
}
