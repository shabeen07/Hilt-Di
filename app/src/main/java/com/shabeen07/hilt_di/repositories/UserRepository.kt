package com.shabeen07.hilt_di.repositories

import androidx.lifecycle.LiveData
import com.shabeen07.hilt_di.domain.ApiService
import com.shabeen07.hilt_di.models.UserResponseItem
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(val apiService: ApiService) {

    var job: CompletableJob? = null

    fun getUsers(): LiveData<List<UserResponseItem>> {
        job = Job()
        return object : LiveData<List<UserResponseItem>>() {
            override fun onActive() {
                super.onActive()
                job?.let { job1 ->
                    CoroutineScope(Dispatchers.IO + job1).launch {
                        val users = apiService.getUser()
                        withContext(Dispatchers.Main) {
                            value = users
                            job1.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }

}