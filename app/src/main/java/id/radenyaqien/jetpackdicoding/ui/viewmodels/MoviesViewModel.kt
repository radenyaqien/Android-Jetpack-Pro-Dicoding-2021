package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.utils.EspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {


    private val _response = MutableLiveData<List<PopularMovies>>()
    val response: LiveData<List<PopularMovies>> = _response

    fun getmoviesApi() = viewModelScope.launch {
        EspressoIdlingResource.increment()
        _response.postValue(homeRepository.getMovies())
        EspressoIdlingResource.decrement()
    }

}