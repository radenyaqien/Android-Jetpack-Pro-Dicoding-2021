package id.radenyaqien.jetpackdicoding

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import id.radenyaqien.jetpackdicoding.utils.Dummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class MainActivityTest {
    private val dummyMovies = Dummy.generatesDummyMovies()
    private val dummyTvSeries = Dummy.generatesDummyTvShows()


    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    /**
     * Memastikan rv_tv dalam keadaan tampil
     * Memastikan data movies tidak Null
     * Memastikan Jumlah data sesuai yang di harapkan
     *  Scroll rv_tv ke posisi terakhir
     */
    @Test
    fun loadTvSeries() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyTvSeries.size
                )
        )
        assertNotNull(dummyTvSeries)
        assertEquals(11, dummyTvSeries.size)
    }

    /**
     * Memastikan rv_movies dalam keadaan tampil
     * Memastikan data movies tidak Null
     * Memastikan Jumlah data sesuai yang di harapkan
     *  Scroll rv_movies ke posisi terakhir
     */
    @Test
    fun getMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyMovies.size
                )
        )
        assertNotNull(dummyMovies)
        assertEquals(11, dummyMovies.size)
    }


}
