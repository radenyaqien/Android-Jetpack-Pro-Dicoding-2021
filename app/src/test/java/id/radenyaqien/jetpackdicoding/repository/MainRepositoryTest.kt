package id.radenyaqien.jetpackdicoding.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import id.radenyaqien.jetpackdicoding.LiveDataUtils
import id.radenyaqien.jetpackdicoding.RemoteDataSource
import id.radenyaqien.jetpackdicoding.utils.Dummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MainRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = MainRepository(remote)

    @Test
    fun getMovies() {
        val dumy = Dummy.generatesDummyMovies()
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(dumy)
            null
        }.`when`(remote).getAllMovies(any())
        val courseEntities = LiveDataUtils.getValue(repository.getMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(courseEntities)
        assertEquals(dumy.size.toLong(), courseEntities.size.toLong())

    }

    @Test
    fun getTVShows() {
        val dumy = Dummy.generatesDummyTvShows()
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(dumy)
            null
        }.`when`(remote).getAllTvshows(any())
        val live = LiveDataUtils.getValue(repository.getTVShows())
        verify(remote).getAllTvshows(any())
        assertNotNull(live)
        assertEquals(dumy.size.toLong(), live.size.toLong())
    }
}