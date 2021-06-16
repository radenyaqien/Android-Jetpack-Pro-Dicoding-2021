package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.repository.MyDataSource
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
class TvViewModelTest {

    @Mock
    private lateinit var remoteDataSource: MyDataSource

    @get:Rule
    val dispatcher = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<PopularTv>>
    private val homeRepository: HomeRepository = mock()
    private lateinit var viewModel: TvViewModel


    @Before
    fun setUp() {

        remoteDataSource = mock(remoteDataSource.javaClass)

        viewModel = TvViewModel(homeRepository)

    }

    @Test
    fun getPopularTvShows() = runBlocking {

        val dumy = Dummy.getDummyPopular()
        val live = MutableLiveData<List<PopularTv>>()
        live.value = dumy

        `when`(homeRepository.getPopularTv()).thenReturn(dumy)
        viewModel.getTv()
        val con = viewModel.response.value
        verify(homeRepository).getPopularTv()
        assertNotNull(con)
        if (con != null) {
            assertEquals(1, con.size)
        }
        viewModel.response.observeForever(observer)
        verify(observer).onChanged(dumy)
    }

}






