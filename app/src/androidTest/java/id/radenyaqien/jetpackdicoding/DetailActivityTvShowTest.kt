package id.radenyaqien.jetpackdicoding

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import id.radenyaqien.jetpackdicoding.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailActivityTvShowTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadDetailTv() {
        onView(withText(R.string.tvshows)).perform(click())
        onView(withId(R.id.rv_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )
        onView(withId(R.id.img_thumbnail))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_name))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_oveview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_year))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_genre))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}