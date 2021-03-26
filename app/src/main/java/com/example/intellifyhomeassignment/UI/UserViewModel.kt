package com.example.intellifyhomeassignment.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.gms.tasks.Tasks.await
import kotlinx.coroutines.Dispatchers

class UserViewModel(private val repository: UserRepository= UserRepository()): ViewModel() {
  val responseLiveData = liveData(Dispatchers.IO) {
    emit(repository.getResponseFromRealtimeDatabaseUsingCoroutines())
  }
}
