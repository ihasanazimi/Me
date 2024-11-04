package ir.ha.meproject

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import ir.ha.meproject.common.espresso_util.MyCountingIdlingResource
import ir.ha.meproject.common.espresso_util.MyIdlingResource
import ir.ha.meproject.presentation.MainActivity
import ir.ha.meproject.presentation.features.fragments.more.MoreFragment
import ir.ha.meproject.presentation.features.fragments.more.MoreFragmentArgs
import ir.ha.meproject.presentation.features.fragments.splash.SplashFragment
import ir.ha.meproject.presentation.test_activity.TestActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.reflect.Field

@RunWith(AndroidJUnit4::class)
@MediumTest
class UiTests {

    val TAG = this::class.java.simpleName

    @Before
    fun setup() {
        Log.i(TAG, "setup: ")
    }

    @After
    fun tearDown() {
        Log.i(TAG, "tearDown: ")
    }

    @Test
    fun check_navigate_and_scenario_from_splash_to_lastFragment_is_correct_or_no_by_simpleIdleResource_TEST() {
        Log.i(TAG, "check_navigate_and_scenario_from_splash_to_lastFragment_is_correct_or_no_by_simpleIdleResource_TEST: ")

        var idleResources: MyIdlingResource? = null
        val activityScenarioRule = ActivityScenario.launch(MainActivity::class.java)

        activityScenarioRule.onActivity { activity ->
            val navHostFragment = activity.supportFragmentManager.primaryNavigationFragment
            val splashFragment = navHostFragment?.childFragmentManager?.fragments?.find { it is SplashFragment } as? SplashFragment
            if (splashFragment != null) {
                idleResources = splashFragment.getIdlingResource()
                IdlingRegistry.getInstance().register(idleResources)
            }
        }

        onView(withId(R.id.goToNextPage)).check(matches(isDisplayed()))
        onView(withId(R.id.goToNextPage)).perform(click())
        IdlingRegistry.getInstance().unregister(idleResources)
        activityScenarioRule.close()
    }

    @Test
    fun check_navigate_and_scenario_from_splash_to_lastFragment_is_correct_or_no_by_counting_TEST() {
        Log.i(TAG, "check_navigate_and_scenario_from_splash_to_lastFragment_is_correct_or_no_by_counting_TEST: ")

        var idleResources: MyCountingIdlingResource? = null
        val activityScenarioRule = ActivityScenario.launch(MainActivity::class.java)

        activityScenarioRule.onActivity { activity ->

            val navHostFragment = activity.supportFragmentManager.primaryNavigationFragment
            val splashFragment = navHostFragment?.childFragmentManager?.fragments?.find { it is SplashFragment } as? SplashFragment

            if (splashFragment != null) {
                idleResources = splashFragment.getMyCountingIdlingResource()
                IdlingRegistry.getInstance().register(idleResources)
            }
        }

        onView(withId(R.id.goToNextPage)).check(matches(isDisplayed()))
        onView(withId(R.id.goToNextPage)).perform(click())
        IdlingRegistry.getInstance().unregister(idleResources)
        activityScenarioRule.close()
    }

    @Test
    fun launch_more_fragment_and_check_argument() {

        val myIdlingResource = MyIdlingResource("launch_more_fragment_and_check_argument").apply {
            setIdleState(false)
            IdlingRegistry.getInstance().register(this)
        }

        val argumentValue = "Sample Argument"
        val args = MoreFragmentArgs(argumentValue).toBundle()

        val activityScenario = ActivityScenario.launch(TestActivity::class.java)
        activityScenario.onActivity { activity ->
            val fragment = MoreFragment().apply {
                arguments = args
            }
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow()
        }
        myIdlingResource.setIdleState(true)
        onView(withId(R.id.argumentsTV)).check(matches(withText(argumentValue)))
        IdlingRegistry.getInstance().unregister(myIdlingResource)
        activityScenario.close()
    }
}
