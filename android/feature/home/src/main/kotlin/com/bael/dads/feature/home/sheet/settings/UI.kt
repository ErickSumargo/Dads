package com.bael.dads.feature.home.sheet.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.databinding.ItemGroupSettingsBinding
import com.bael.dads.feature.home.databinding.ItemSettingBinding
import com.bael.dads.feature.home.databinding.SheetSettingsBinding
import com.bael.dads.feature.home.databinding.SheetSettingsBinding.inflate
import com.bael.dads.library.preference.Preference
import com.bael.dads.library.presentation.ext.readText
import com.bael.dads.library.presentation.sheet.BaseSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseSheet<SheetSettingsBinding, Renderer, Event, ViewModel>(),
    Renderer {
    @Inject
    lateinit var preference: Preference

    override val fullHeight: Boolean = false

    override val viewModel: ViewModel by viewModels()

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SheetSettingsBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded() {
        setupView()
    }

    override suspend fun action(event: Event) {}

    private suspend fun setupView() {
        viewBinding.settingsContainer.also { container ->
            container.removeAllViews()

            container.addView(createNotificationGroup())
            container.addView(createThemeGroup())
        }
    }

    private suspend fun createNotificationGroup(): ViewGroup {
        return ItemGroupSettingsBinding
            .inflate(layoutInflater).also { viewBinding ->
                viewBinding.groupText.also { view ->
                    view.text = readText(resId = R.string.notification)
                }

                viewBinding.groupContainer.also { container ->
                    container.addView(createNewFeedReminderSetting())
                }
            }.root
    }

    private suspend fun createThemeGroup(): ViewGroup {
        return ItemGroupSettingsBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.groupText.also { view ->
                view.text = readText(resId = R.string.theme)
            }

            viewBinding.groupContainer.also { container ->
                container.addView(createNightModeSetting())
            }
        }.root
    }

    private suspend fun createNewFeedReminderSetting(): View {
        return ItemSettingBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.descriptionText.also { view ->
                view.text = readText(resId = R.string.new_feed_reminder)
            }

            viewBinding.toggleSwitch.also { switch ->
                switch.isChecked = preference.read(
                    key = NEW_FEED_REMINDER_PREFERENCE,
                    defaultValue = true
                )

                switch.setOnCheckedChangeListener { _, isChecked ->
                    onCheckedChangeNewFeedReminder(isChecked)
                }
            }
        }.root
    }

    private fun onCheckedChangeNewFeedReminder(isChecked: Boolean) {
        lifecycleScope.launch {
            preference.write(key = NEW_FEED_REMINDER_PREFERENCE, isChecked)
        }
    }

    private suspend fun createNightModeSetting(): View {
        return ItemSettingBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.descriptionText.also { view ->
                view.text = readText(resId = R.string.night_mode)
            }

            viewBinding.toggleSwitch.also { switch ->
                switch.isChecked = preference.read(
                    key = NIGHT_THEME_PREFERENCE,
                    defaultValue = false
                )

                switch.setOnCheckedChangeListener { _, isChecked ->
                    onCheckedChangeNightMode(isChecked)
                }
            }
        }.root
    }

    private fun onCheckedChangeNightMode(isChecked: Boolean) {
        lifecycleScope.launch {
            preference.write(key = NIGHT_THEME_PREFERENCE, isChecked)
            setDefaultNightMode(MODE_NIGHT_YES.takeIf { isChecked } ?: MODE_NIGHT_NO)
        }
    }

    private companion object {
        const val NEW_FEED_REMINDER_PREFERENCE: String = "new_feed_reminder"

        const val NIGHT_THEME_PREFERENCE: String = "night_theme"
    }
}
