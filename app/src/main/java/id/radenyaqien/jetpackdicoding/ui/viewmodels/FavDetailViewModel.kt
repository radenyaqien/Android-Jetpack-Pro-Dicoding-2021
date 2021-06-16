package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavDetailViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _resultInsertmovies = MutableLiveData<Boolean>()
    val resultInsertmoviesDb: LiveData<Boolean>
        get() = _resultInsertmovies

    fun insertmovies(moviesEntity: MoviesEntity) {
        viewModelScope.launch {
            try {
                repository.insertMovies(moviesEntity)
                _resultInsertmovies.value = true
            } catch (e: Exception) {
                _resultInsertmovies.value = false
            }

        }
    }

    fun getMoviesById(id: Int) =
        repository.getMoviesById(id)

    private val _resultdeleteMovies = MutableLiveData<Boolean>()
    val resultdeleteMovies: LiveData<Boolean>
        get() = _resultdeleteMovies

    fun deleteMovies(moviesEntity: MoviesEntity) {
        viewModelScope.launch {
            try {
                repository.deleteMovies(moviesEntity)
                _resultdeleteMovies.value = true
            } catch (e: Exception) {
                _resultdeleteMovies.value = false
            }

        }
    }


    private val _resultInsertTv = MutableLiveData<Boolean>()
    val resultInsertTv: LiveData<Boolean>
        get() = _resultInsertTv

    fun inserttv(tvEntity: TvEntity) {
        viewModelScope.launch {
            try {
                repository.insertTv(tvEntity)
                _resultInsertTv.value = true
            } catch (e: Exception) {
                _resultInsertTv.value = false
            }

        }
    }

    fun getTvById(id: Int) =
        repository.getTvById(id)

    private val _resultdeleteTv = MutableLiveData<Boolean>()
    val resultDeletetv: LiveData<Boolean>
        get() = _resultdeleteTv

    fun deletetv(tvEntity: TvEntity) {
        viewModelScope.launch {
            try {
                repository.deleteTv(tvEntity)
                _resultdeleteTv.value = true
            } catch (e: Exception) {
                _resultdeleteTv.value = false
            }

        }
    }
}