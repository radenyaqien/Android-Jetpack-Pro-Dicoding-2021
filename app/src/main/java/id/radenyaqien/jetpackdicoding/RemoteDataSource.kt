package id.radenyaqien.jetpackdicoding

import android.os.Handler
import android.os.Looper
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.TvShows
import id.radenyaqien.jetpackdicoding.retrofit.GithubInterface
import id.radenyaqien.jetpackdicoding.utils.Dummy

class RemoteDataSource(private val dataSource: GithubInterface) {
    private val handler = Handler(Looper.getMainLooper())


    suspend fun getAllTv() = dataSource.getPopularTv(BuildConfig.api_key).results


    fun getAllMovies(callback: LoadMoviesCallback) {
        handler.postDelayed({ callback.onAllMoviesReceived(Dummy.generatesDummyMovies()) }, 1000L)
    }

    fun getAllTvshows(callback: LoadTvCallback) {
        handler.postDelayed({ callback.onAllTvReceived(Dummy.generatesDummyTvShows()) }, 1000L)
    }


    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponses: List<Movies>)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponses: List<TvShows>)
    }


}