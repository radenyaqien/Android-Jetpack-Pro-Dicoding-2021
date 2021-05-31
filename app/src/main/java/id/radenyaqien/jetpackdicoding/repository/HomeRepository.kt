package id.radenyaqien.jetpackdicoding.repository

import id.radenyaqien.jetpackdicoding.RemoteDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val dataSource: RemoteDataSource) {

    suspend fun getPopular() = dataSource.getAllTv()


}