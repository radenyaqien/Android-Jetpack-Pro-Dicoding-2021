package id.radenyaqien.jetpackdicoding.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.radenyaqien.jetpackdicoding.RemoteDataSource
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.TvShows
import javax.inject.Inject

class MainRepository @Inject constructor(private val dataSource: RemoteDataSource) {


    fun getMovies(): LiveData<List<Movies>> {
        val courseResults = MutableLiveData<List<Movies>>()
        dataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<Movies>) {
                courseResults.postValue(moviesResponses)
            }
        })

        return courseResults
    }

    fun getTVShows(): LiveData<List<TvShows>> {
        val result = MutableLiveData<List<TvShows>>()
        dataSource.getAllTvshows(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvResponses: List<TvShows>) {
                result.postValue(tvResponses)
            }
        })

        return result
    }
}