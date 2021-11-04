package com.bael.dads.feature.home.sheet.settings

import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.sheet.settings.UI.Companion.NEW_FEED_REMINDER_PREFERENCE
import com.bael.dads.feature.home.sheet.settings.UI.Companion.NIGHT_THEME_PREFERENCE
import com.bael.dads.library.instrumentation.matcher.MatcherParams
import com.bael.dads.library.preference.Preference
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/05/21.
 */

@HiltAndroidTest
internal class UITest : BaseSheetTest() {
    @Inject
    lateinit var preference: Preference

    override fun setupTest() {}

    @Test
    fun whenSheetShown_settingsMenuShouldShow() {
        runTest {
            // when
            launch<UI>()

            // then
            assertViewDisplayed(
                params = MatcherParams(
                    text = context.readText(resId = R.string.notification)
                )
            )
            assertViewDisplayed(
                params = MatcherParams(
                    text = context.readText(resId = R.string.theme)
                )
            )
        }
    }

    @Test
    fun whenNewFeedReminderSwitchToggled_valueShouldBeStored() {
        runTest {
            // given
            preference.write(key = NEW_FEED_REMINDER_PREFERENCE, value = false)

            // when
            launch<UI>()

            clickView(
                params = MatcherParams(
                    id = R.id.toggleSwitch,
                    sibling = MatcherParams(
                        text = context.readText(resId = R.string.new_feed_reminder)
                    )
                )
            )

            // then
            assertSwitchChecked(
                params = MatcherParams(
                    id = R.id.toggleSwitch,
                    sibling = MatcherParams(
                        text = context.readText(resId = R.string.new_feed_reminder)
                    )
                ),
                checked = true
            )

            assertThat(
                preference.read(
                    key = NEW_FEED_REMINDER_PREFERENCE,
                    defaultValue = false
                )
            ).isTrue()
        }
    }

    @Test
    fun whenNightModeSwitchToggled_valueShouldBeStored() {
        runTest {
            // given
            preference.write(key = NIGHT_THEME_PREFERENCE, value = false)

            // when
            launch<UI>()

            clickView(
                params = MatcherParams(
                    id = R.id.toggleSwitch,
                    sibling = MatcherParams(
                        text = context.readText(resId = R.string.night_mode)
                    )
                )
            )

            // then
            assertSwitchChecked(
                params = MatcherParams(
                    id = R.id.toggleSwitch,
                    sibling = MatcherParams(
                        text = context.readText(resId = R.string.night_mode)
                    )
                ),
                checked = true
            )

            assertThat(
                preference.read(
                    key = NIGHT_THEME_PREFERENCE,
                    defaultValue = false
                )
            ).isTrue()
        }
    }

    override fun clearTest() {}
}
