package com.ahsan.gitrepos.ui.main_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahsan.gitrepos.data.remote.models.ReposResponse
import com.ahsan.gitrepos.data.remote.models.ResponseWrapper
import com.ahsan.gitrepos.data.remote.repositories.RepoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepoRepository): ViewModel() {

    val repoResponse = MutableLiveData<ResponseWrapper<ReposResponse>>()
    val moreRepoResponse = MutableLiveData<ResponseWrapper<ReposResponse>>()
    var isLoading= MutableLiveData<Boolean>()
    var currentPage = 0

    init {
        getAllRepos()
    }

    fun getAllRepos() {
        isLoading.postValue(true)
        viewModelScope.launch{
            val response = repository.getAllRepos(currentPage)
            withContext(Dispatchers.Main){
                isLoading.postValue(false)
                repoResponse.value = response
            }
        }
    }

    fun getMoreRepos() {
        isLoading.postValue(true)
        viewModelScope.launch{
            val response = repository.getAllRepos(currentPage)
            withContext(Dispatchers.Main){
                isLoading.postValue(false)
                repoResponse.value = response
            }
        }
    }
}