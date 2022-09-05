package com.vishal.kotlin_github.view

import android.service.autofill.Validators.not
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.base.CharMatcher.`is`
import com.vishal.kotlin_github.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private lateinit var activityScenario: ActivityScenario<LoginActivity>
    @Before
    fun setUp(){
        activityScenario = ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    fun isActivityInView() {
        onView(withId(R.id.container))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isEditTextInActivity() {
        onView(withId(R.id.username))
            .check(matches(isDisplayed()))
    }

    @Test
    fun isButtonInActivity() {
        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
    }

    @Test
    fun emptyUsernameUIFlow() {
        onView(withId(R.id.username)).perform(typeText(""))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.button)).perform(click())

    }

    @Test
    fun nonEmptyUsernameUIFlow() {
        onView(withId(R.id.username)).perform(typeText("vishal-sengar-dtu"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
    }

}