package com.bael.dads.lib.instrumentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseUITest {

    fun isDisplayed(id: Int) {
        onView(withId(id))
            .check(matches(isDisplayed()))
    }

    fun isDisplayed(text: String) {
        onView(withText(text))
            .check(matches(isDisplayed()))
    }

    fun isDisplayed(id: Int, text: String) {
        onView(
            allOf(
                withId(id),
                withText(text)
            )
        ).check(matches(isDisplayed()))
    }
}
