package id.radenyaqien.jetpackdicoding

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import id.radenyaqien.jetpackdicoding.utils.Dummy
import org.junit.Before
import org.junit.Test

class DetailActivityMoviesTest {

    private val dummyMovies = Dummy.generatesDummyMovies()


    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadDetailTv() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.img_thumbnail)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_name)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_oveview)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_year)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_name)).check(matches(withText(dummyMovies[0].name)))
        onView(withId(R.id.txt_genre)).check(matches(withText(dummyMovies[0].genre)))
        onView(withId(R.id.txt_year)).check(matches(withText(dummyMovies[0].Year)))
        onView(withId(R.id.txt_oveview)).check(matches(withText(dummyMovies[0].overview)))

    }
}