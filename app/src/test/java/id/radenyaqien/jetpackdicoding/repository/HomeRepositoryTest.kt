package id.radenyaqien.jetpackdicoding.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import id.radenyaqien.jetpackdicoding.LiveDataUtils
import id.radenyaqien.jetpackdicoding.PagedListUtil
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity
import id.radenyaqien.jetpackdicoding.utils.Dummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@ExperimentalCoroutinesApi
class HomeRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dataSource: MyDataSource = mock(MyDataSource::class.java)
    private val repository: FakeHomeRepository = FakeHomeRepository(dataSource)


    private val TvResponse = Dummy.getDummyPopular()
    private val MoviesResponse = Dummy.getDummyMovies()
    private val moviesID = MoviesResponse[0].id
    private val moviesEntity = Dummy.getdummymovies(moviesID)
    private val TvShowsID = TvResponse[0].id
    private val TvShowsEntity = Dummy.getdummyTv(TvShowsID)

    @ExperimentalCoroutinesApi
    @Test
    fun getPopularTv() = runBlockingTest {
        val data = Dummy.getDummyPopular()

        whenever(repository.getPopularTv()).thenReturn(data)

        val response = repository.getPopularTv()
        verify(dataSource).getAllTv()
        assertNotNull(response)
        assertEquals(1, data.size)


    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovies() = runBlockingTest {
        val data = Dummy.getDummyMovies()

        whenever(repository.getMovies()).thenReturn(data)

        val response = repository.getMovies()
        verify(dataSource).getAllMovies()
        assertNotNull(response)
        assertEquals(1, data.size)

    }

    @Test
    fun getTvLocal() {
        val dataSourceFactory = mock<DataSource.Factory<Int, TvEntity>>()
        `when`(dataSource.getTvLocal()).thenReturn(dataSourceFactory)
        repository.getTvLocal()

        val courseEntities = PagedListUtil.mockPagedList(Dummy.getDummyPopular())
        //dataSource.getTvLocal()
        verify(dataSource).getTvLocal()
        assertNotNull(courseEntities)
        assertEquals(TvResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getMoviesLocal() {
        val dataSourceFactory = mock<DataSource.Factory<Int, MoviesEntity>>()
        `when`(dataSource.getMoviesLocal()).thenReturn(dataSourceFactory)
        repository.getMoviesLocal()
        val courseEntities = PagedListUtil.mockPagedList(Dummy.getDummyMovies())


        verify(dataSource).getMoviesLocal()
        assertNotNull(courseEntities)
        assertEquals(MoviesResponse.size.toLong(), courseEntities.size.toLong())

    }

    /*
    testing add tv & delete tv show
     */
    @Test
    fun deleteTv() = runBlockingTest {
        val data = Dummy.getdummyTv(TvShowsID)
        repository.deleteTv(data)
        verify(dataSource).deleteTv(data)
    }

    @Test
    fun insertTv() = runBlockingTest {
        val data = Dummy.generateTvEntity()
        repository.insertTv(data)
        verify(dataSource).insertTv(data)
    }

    @Test
    fun deleteMovies() = runBlockingTest {
        val data = Dummy.generateMoviesEntity()
        repository.deleteMovies(data)
        verify(dataSource).deleteMovies(data)
    }

    @Test
    fun insertMovies() = runBlockingTest {
        val data = Dummy.generateMoviesEntity()
        repository.insertMovies(data)
        verify(dataSource).insertMovies(data)
    }

    @Test
    fun getMoviesbyId() = runBlockingTest {
        val dummyModules = MutableLiveData<MoviesEntity>()
        dummyModules.value = Dummy.getDummyMoviesEntitiy()[0]
        `when`(dataSource.getMoviesById(moviesID)).thenReturn(dummyModules)

        val courseEntities = LiveDataUtils.getValue(repository.getMoviesById(moviesID))
        verify(dataSource).getMoviesById(moviesID)
        assertNotNull(courseEntities)
        assertEquals(moviesEntity.id, courseEntities.id)

    }

    @Test
    fun getTvShowsbyId() = runBlockingTest {
        val dummyModules = MutableLiveData<TvEntity>()
        dummyModules.value = Dummy.getDummyTvEntity()[0]
        `when`(dataSource.getTvById(TvShowsID)).thenReturn(dummyModules)

        val courseEntities = LiveDataUtils.getValue(repository.getTvById(TvShowsID))
        verify(dataSource).getTvById(TvShowsID)
        assertNotNull(courseEntities)
        assertEquals(TvShowsEntity.id, courseEntities.id)

    }
}