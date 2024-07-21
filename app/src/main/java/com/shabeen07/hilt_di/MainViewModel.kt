package com.shabeen07.hilt_di

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shabeen07.hilt_di.models.UserResponseItem
import com.shabeen07.hilt_di.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            getUsers();
        }
    }

    fun getUsers(): LiveData<List<UserResponseItem>> {
        return userRepository.getUsers()
    }
}