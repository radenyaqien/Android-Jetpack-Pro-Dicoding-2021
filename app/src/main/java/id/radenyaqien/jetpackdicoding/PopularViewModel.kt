package id.radenyaqien.jetpackdicoding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    private val _response = MutableLiveData<List<PopularMovies>>()
    val response: LiveData<List<PopularMovies>> = _response

    fun getmoviesOl() = viewModelScope.launch {
        _response.postValue(homeRepository.getPopular())
    }
}