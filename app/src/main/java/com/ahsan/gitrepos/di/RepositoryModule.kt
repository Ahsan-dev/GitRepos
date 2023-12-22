package com.ahsan.gitrepos.di

import com.ahsan.gitrepos.data.remote.repositories.RepoRepository
import com.ahsan.gitrepos.data.remote.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providesRepository(apiService: ApiService): RepoRepository{
        return RepoRepository(apiService)
    }
}