package id.radenyaqien.jetpackdicoding

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(11, movies.size)

    }

    @Test
    fun getTvShows() {
        val tvshow = viewModel.getMovies()
        assertNotNull(tvshow)
        assertEquals(11, tvshow.size)

    }
}