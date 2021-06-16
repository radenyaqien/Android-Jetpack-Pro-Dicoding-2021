package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {


    fun getTvFav(): LiveData<PagedList<TvEntity>> = homeRepository.getTvLocal()
    fun getMoviesFav(): LiveData<PagedList<MoviesEntity>> = homeRepository.getMoviesLocal()
}