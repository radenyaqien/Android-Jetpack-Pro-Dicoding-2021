package id.radenyaqien.jetpackdicoding

import androidx.lifecycle.ViewModel
import id.radenyaqien.jetpackdicoding.utils.Dummy

class MainViewModel : ViewModel() {

    fun getMovies() = Dummy.generatesDummyMovies()

    fun getTvShows() = Dummy.generatesDummyTvShows()


}