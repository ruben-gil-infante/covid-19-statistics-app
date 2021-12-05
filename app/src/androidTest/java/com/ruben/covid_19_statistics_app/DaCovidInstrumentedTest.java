package com.ruben.covid_19_statistics_app;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;


import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import com.ruben.covid_19_statistics_app.ui.views.MainActivity;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DaCovidInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void noRegionCoincidenceTest() {
        // Write on the region search toolbar a country name that does not exists
        sleep();
        onView(withHint(R.string.enter_the_country)).perform(typeText("asdfasdf"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.list_with_finder_layout_no_elements_find_wrapper)).check(matches(isDisplayed()));
    }

    @Test
    public void selectRegionTest() {
        sleep();
        onView(withHint(R.string.enter_the_country)).perform(typeText("China"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.item_name)).perform(click());
    }

    @Test
    public void selectProvinceTest() {
        sleep();
        onView(withHint(R.string.enter_the_country)).perform(typeText("China"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.item_name)).perform(click());
        sleep();
        onView(withText(R.string.anhui)).perform(click());
    }

    // This test is a happy path test :)
    @Test
    public void selectRegionAsFavourite() {
        sleep();
        onView(withHint(R.string.enter_the_country)).perform(typeText("China"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.item_name)).perform(click());
        sleep();
        onView(withText(R.string.anhui)).perform(click());
        sleep();
        onView(withId(R.id.report_fragment_star_button)).perform(click());
    }

    @Test
    public void closeFavDialog() {
        sleep();
        onView(withHint(R.string.enter_the_country)).perform(typeText("China"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.item_name)).perform(click());
        sleep();
        onView(withText(R.string.anhui)).perform(click());
        sleep();
        onView(withId(R.id.report_fragment_star_button)).perform(click());
    }

    @Test
    public void testRetryButtonFromErrorLayout() {
        // TODO: Implement that test
        sleep();
        onView(withHint(R.string.enter_the_country)).perform(typeText("Thailand"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.item_name)).perform(click());
        sleep();
        onView(withText(R.string.retry)).perform(click());
        sleep();
        onView(withText(R.string.something_went_wrong)).check(matches(isDisplayed()));
    }
}