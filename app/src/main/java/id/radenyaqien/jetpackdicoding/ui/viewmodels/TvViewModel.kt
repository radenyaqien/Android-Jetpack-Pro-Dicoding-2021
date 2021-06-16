package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.utils.EspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    private val _response = MutableLiveData<List<PopularTv>>()
    val response: LiveData<List<PopularTv>> = _response

    fun getTv() = viewModelScope.launch {
        EspressoIdlingResource.increment()
        _response.postValue(homeRepository.getPopularTv())
        EspressoIdlingResource.decrement()
    }


}