package com.bael.dads.library.instrumentation.ui

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.bael.dads.library.instrumentation.matcher.MatcherParams
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

/**
 * Created by ErickSumargo on 01/04/21.
 */

abstract class BaseUITest {

    fun assertViewDisplayed(params: MatcherParams) {
        val matcher = selectMatcher(params) ?: return
        onView(matcher).check(matches(isDisplayed()))
    }

    fun assertSwitchChecked(params: MatcherParams, checked: Boolean) {
        val matcher = selectMatcher(params) ?: return
        onView(matcher).check(matches(isChecked().takeIf { checked } ?: isNotChecked()))
    }

    fun clickView(params: MatcherParams) {
        val matcher = selectMatcher(params) ?: return
        onView(matcher).perform(click())
    }

    private fun selectMatcher(params: MatcherParams?): Matcher<View>? {
        params ?: return null

        val selfMatcher = selectMatcher(id = params.id, text = params.text)
        val parentMatcher = selectMatcher(params = params.parent)
        val siblingMatcher = selectMatcher(params = params.sibling)

        return when {
            parentMatcher != null && siblingMatcher != null -> {
                allOf(
                    selfMatcher,
                    withParent(parentMatcher),
                    hasSibling(siblingMatcher)
                )
            }
            parentMatcher != null -> {
                allOf(
                    selfMatcher,
                    withParent(parentMatcher)
                )
            }
            siblingMatcher != null -> {
                allOf(
                    selfMatcher,
                    hasSibling(siblingMatcher)
                )
            }
            else -> {
                allOf(selfMatcher)
            }
        }
    }

    private fun selectMatcher(id: Int = -1, text: String = ""): Matcher<View> {
        return when {
            id != -1 && text.isNotBlank() -> allOf(withId(id), withText(text))
            id != -1 -> allOf(withId(id))
            else -> allOf(withText(text))
        }
    }
}
