package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.repository.MyDataSource
import id.radenyaqien.jetpackdicoding.utils.Dummy
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @Mock
    private lateinit var remoteDataSource: MyDataSource

    @get:Rule
    val dispatcher = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<PopularMovies>>
    private val homeRepository: HomeRepository = mock()
    private lateinit var viewModel: MoviesViewModel


    @Before
    fun setUp() {
        remoteDataSource = Mockito.mock(remoteDataSource.javaClass)
        viewModel = MoviesViewModel(homeRepository)
    }

    @Test
    fun getPopularmovies() = runBlocking {

        val dumy = Dummy.getDummyMovies()
        val live = MutableLiveData<List<PopularMovies>>()
        live.value = dumy


        `when`(homeRepository.getMovies()).thenReturn(dumy)
        viewModel.getmoviesApi()
        val con = viewModel.response.value
        verify(homeRepository).getMovies()
        Assert.assertNotNull(con)
        if (con != null) {
            Assert.assertEquals(1, con.size)
        }

        viewModel.response.observeForever(observer)
        verify(observer).onChanged(dumy)
    }

}