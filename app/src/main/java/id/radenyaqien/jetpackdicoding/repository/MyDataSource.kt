package id.radenyaqien.jetpackdicoding.repository

import androidx.paging.DataSource
import id.radenyaqien.jetpackdicoding.BuildConfig
import id.radenyaqien.jetpackdicoding.retrofit.GithubInterface
import id.radenyaqien.jetpackdicoding.room.FavoriteDAO
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity
import javax.inject.Inject

class MyDataSource @Inject constructor(
    private val dataSource: GithubInterface,
    private val favoriteDAO: FavoriteDAO
) {

    //remote
    suspend fun getAllTv() = dataSource.getPopularTv(BuildConfig.api_key).results
    suspend fun getAllMovies() = dataSource.getPopularMovies(BuildConfig.api_key).results

    //local
    fun getTvLocal(): DataSource.Factory<Int, TvEntity> = favoriteDAO.getAllTv()
    fun getTvById(id: Int) = favoriteDAO.getTvbyId(id)
    suspend fun deleteTv(tvEntity: TvEntity) = favoriteDAO.deleteTv(tvEntity)
    suspend fun insertTv(tvEntity: TvEntity) = favoriteDAO.addTv(tvEntity)

    fun getMoviesLocal(): DataSource.Factory<Int, MoviesEntity> = favoriteDAO.getAllmovies()
    fun getMoviesById(id: Int) = favoriteDAO.getMoviesbyId(id)
    suspend fun deleteMovies(moviesEntity: MoviesEntity) = favoriteDAO.deleteMovies(moviesEntity)
    suspend fun insertMovies(moviesEntity: MoviesEntity) = favoriteDAO.addMovies(moviesEntity)
}