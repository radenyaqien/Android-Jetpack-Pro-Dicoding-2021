package id.radenyaqien.jetpackdicoding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.utils.Dummy
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularViewModelTest {


    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @get:Rule
    val dispatcher = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<PopularMovies>>
    private val homeRepository: HomeRepository = mock()
    private lateinit var viewModel: PopularViewModel


    @Before
    fun setUp() {

        remoteDataSource = mock(remoteDataSource.javaClass)

        viewModel = PopularViewModel(homeRepository)

    }

    @Test
    fun getPopularmovies() = runBlocking {

        val dumy = Dummy.getDummyPopular()
        val live = MutableLiveData<List<PopularMovies>>()
        live.value = dumy

        `when`(homeRepository.getPopular()).thenReturn(dumy)
        viewModel.getmoviesOl()
        val con = viewModel.response.value
        verify(homeRepository).getPopular()
        assertNotNull(con)
        assertEquals(1, con?.size)

        viewModel.response.observeForever(observer)
        verify(observer).onChanged(dumy)
    }


}






