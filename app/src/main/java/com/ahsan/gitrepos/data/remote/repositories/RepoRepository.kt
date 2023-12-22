package com.ahsan.gitrepos.data.remote.repositories

import com.ahsan.gitrepos.data.remote.models.ReposResponse
import com.ahsan.gitrepos.data.remote.models.ResponseWrapper
import com.ahsan.gitrepos.data.remote.services.ApiService
import java.lang.Exception
import javax.inject.Inject

class RepoRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllRepos(page: Int): ResponseWrapper<ReposResponse>{
        return try {
            val response = apiService.getAllRepos(page)
            ResponseWrapper.Success(response)
        }catch (e: Exception){
            ResponseWrapper.Error("Some error")
        }
    }
}