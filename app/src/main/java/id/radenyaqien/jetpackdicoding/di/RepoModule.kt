package id.radenyaqien.jetpackdicoding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.repository.MyDataSource
import id.radenyaqien.jetpackdicoding.retrofit.GithubInterface
import id.radenyaqien.jetpackdicoding.room.FavoriteDAO
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideDataSource(githubInterface: GithubInterface, favoriteDAO: FavoriteDAO) =
        MyDataSource(githubInterface, favoriteDAO)

    @Provides
    @Singleton
    fun provideHomerepository(dataSource: MyDataSource): HomeRepository =
        HomeRepository(dataSource)

}