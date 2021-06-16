package id.radenyaqien.jetpackdicoding.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import id.radenyaqien.jetpackdicoding.repository.HomeRepository
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.room.TvEntity
import id.radenyaqien.jetpackdicoding.utils.Dummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: HomeRepository
    private lateinit var viewModel: FavoriteViewModel

    @Mock
    private lateinit var observer: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var observer2: Observer<PagedList<MoviesEntity>>

    @Before
    fun setUp() {

        viewModel = FavoriteViewModel(repository)
    }

    class PagedTestDataMoviesSources private constructor(private val items: List<MoviesEntity>) :
        PositionalDataSource<MoviesEntity>() {
        companion object {
            fun snapshot(items: List<MoviesEntity> = listOf()): PagedList<MoviesEntity> {
                return PagedList.Builder(PagedTestDataMoviesSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<MoviesEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MoviesEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getFav_movies() = runBlockingTest {
        val expected = MutableLiveData<PagedList<MoviesEntity>>()
        expected.value = PagedTestDataMoviesSources.snapshot(Dummy.getDummyMoviesEntitiy())

        Mockito.`when`(repository.getMoviesLocal()).thenReturn(expected)

        viewModel.getMoviesFav().observeForever(observer2)
        Mockito.verify(observer2).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMoviesFav().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)

    }

    class PagedTestDataSources private constructor(private val items: List<TvEntity>) :
        PositionalDataSource<TvEntity>() {
        companion object {
            fun snapshot(items: List<TvEntity> = listOf()): PagedList<TvEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<TvEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

    @Test
    fun getFav_tvshows() {

        val expected = MutableLiveData<PagedList<TvEntity>>()
        expected.value = PagedTestDataSources.snapshot(Dummy.getDummyTvEntity())

        Mockito.`when`(repository.getTvLocal()).thenReturn(expected)

        viewModel.getTvFav().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvFav().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)

    }
}