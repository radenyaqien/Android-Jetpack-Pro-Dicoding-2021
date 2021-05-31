package id.radenyaqien.jetpackdicoding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.TvShows
import id.radenyaqien.jetpackdicoding.repository.MainRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val homeRepository: MainRepository) : ViewModel() {


    fun getMovies(): LiveData<List<Movies>> = homeRepository.getMovies()

    fun getTvShows(): LiveData<List<TvShows>> = homeRepository.getTVShows()


}