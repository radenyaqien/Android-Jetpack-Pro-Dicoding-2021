package id.radenyaqien.jetpackdicoding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.jetpackdicoding.RemoteDataSource
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.repository.MainRepository
import id.radenyaqien.jetpackdicoding.retrofit.GithubInterface
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideDataSource(githubInterface: GithubInterface) = RemoteDataSource(githubInterface)

    @Provides
    @Singleton
    fun provideHomerepository(dataSource: RemoteDataSource): HomeRepository =
        HomeRepository(dataSource)

    @Provides
    @Singleton
    fun provideMainrepository(dataSource: RemoteDataSource): MainRepository =
        MainRepository(dataSource)
}