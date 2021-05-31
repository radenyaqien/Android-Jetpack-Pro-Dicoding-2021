package id.radenyaqien.jetpackdicoding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.jetpackdicoding.retrofit.ApiService
import id.radenyaqien.jetpackdicoding.retrofit.GithubInterface

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkService(): GithubInterface {
        return ApiService.retrofitClient().create(GithubInterface::class.java)
    }

}