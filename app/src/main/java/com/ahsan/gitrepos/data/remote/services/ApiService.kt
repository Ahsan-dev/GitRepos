package com.ahsan.gitrepos.data.remote.services

import com.ahsan.gitrepos.data.remote.models.ReposResponse
import com.ahsan.gitrepos.data.remote.models.ResponseWrapper
import com.ahsan.gitrepos.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface  ApiService {
    @GET(Constants.REPOS_ENDPOINT)
    suspend fun getAllRepos(
        @Query("page") page:Int,
        @Query("q") q:String="Android",
        @Query("sort") sort:String="stars",
    ):ReposResponse
}