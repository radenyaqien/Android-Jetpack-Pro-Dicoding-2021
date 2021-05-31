package id.radenyaqien.jetpackdicoding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.TvShows
import id.radenyaqien.jetpackdicoding.repository.MainRepository
import id.radenyaqien.jetpackdicoding.utils.Dummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var moviesobserver: Observer<List<Movies>>

    @Mock
    private lateinit var tvobserver: Observer<List<TvShows>>

    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {

        viewModel = MainViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Dummy.generatesDummyMovies()
        val movies = MutableLiveData<List<Movies>>()
        movies.value = dummyMovies

        `when`(repository.getMovies()).thenReturn(movies)
        val courseEntities = viewModel.getMovies().value
        verify(repository).getMovies()
        assertNotNull(courseEntities)
        assertEquals(11, courseEntities?.size)

        viewModel.getMovies().observeForever(moviesobserver)
        verify(moviesobserver).onChanged(dummyMovies)
    }

    @Test
    fun getTvshows() {
        val dummyTvshows = Dummy.generatesDummyTvShows()
        val tvshows = MutableLiveData<List<TvShows>>()
        tvshows.value = dummyTvshows

        `when`(repository.getTVShows()).thenReturn(tvshows)
        val courseEntities = viewModel.getTvShows().value
        verify(repository).getTVShows()
        assertNotNull(courseEntities)
        assertEquals(11, courseEntities?.size)

        viewModel.getTvShows().observeForever(tvobserver)
        verify(tvobserver).onChanged(dummyTvshows)
    }
}