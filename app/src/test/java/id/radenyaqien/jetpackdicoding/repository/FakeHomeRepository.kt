package id.radenyaqien.jetpackdicoding.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity
import javax.inject.Inject

class FakeHomeRepository @Inject constructor(private val dataSource: MyDataSource) {

    //remote
    suspend fun getPopularTv() = dataSource.getAllTv()
    suspend fun getMovies() = dataSource.getAllMovies()

    //local

    fun getTvLocal(): LiveData<PagedList<TvEntity>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(dataSource.getTvLocal(), config).build()
    }

    fun getMoviesLocal(): LiveData<PagedList<MoviesEntity>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(dataSource.getMoviesLocal(), config).build()
    }


    suspend fun deleteTv(tvEntity: TvEntity) {
        dataSource.deleteTv(tvEntity)
    }

    suspend fun insertTv(tvEntity: TvEntity) {
        dataSource.insertTv(tvEntity = tvEntity)
    }


    fun getTvById(id: Int): LiveData<TvEntity> {
        return dataSource.getTvById(id)
    }

    suspend fun deleteMovies(moviesEntity: MoviesEntity) {
        dataSource.deleteMovies(moviesEntity)
    }

    suspend fun insertMovies(moviesEntity: MoviesEntity) {
        dataSource.insertMovies(moviesEntity)
    }


    fun getMoviesById(id: Int): LiveData<MoviesEntity> {
        return dataSource.getMoviesById(id)
    }
}