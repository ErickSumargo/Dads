package com.bael.dads.feature.home.sheet.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.databinding.ItemGroupSettingsBinding
import com.bael.dads.feature.home.databinding.ItemSettingBinding
import com.bael.dads.feature.home.databinding.SheetSettingsBinding
import com.bael.dads.feature.home.databinding.SheetSettingsBinding.inflate
import com.bael.dads.feature.home.preference.Preference
import com.bael.dads.lib.presentation.ext.readText
import com.bael.dads.lib.presentation.sheet.BaseSheet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseSheet<SheetSettingsBinding, Renderer, ViewModel>(),
    Renderer {
    @Inject
    lateinit var preference: Preference

    override val fullHeight: Boolean = false

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SheetSettingsBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded() {
        setupView()
    }

    private fun setupView() {
        viewBinding.settingsContainer.also { container ->
            container.removeAllViews()

            container.addView(createNotificationGroup())
            container.addView(createThemeGroup())
        }
    }

    private fun createNotificationGroup(): ViewGroup {
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

    private fun createThemeGroup(): ViewGroup {
        return ItemGroupSettingsBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.groupText.also { view ->
                view.text = readText(resId = R.string.theme)
            }

            viewBinding.groupContainer.also { container ->
                container.addView(createNightModeSetting())
            }
        }.root
    }

    private fun createNewFeedReminderSetting(): View {
        return ItemSettingBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.descriptionText.also { view ->
                view.text = readText(resId = R.string.new_feed_reminder)
            }

            viewBinding.toggleSwitch.also { switch ->
                switch.isChecked = preference.isNewFeedReminderEnabled

                switch.setOnCheckedChangeListener { _, isChecked ->
                    preference.isNewFeedReminderEnabled = isChecked
                }
            }
        }.root
    }

    private fun createNightModeSetting(): View {
        return ItemSettingBinding.inflate(layoutInflater).also { viewBinding ->
            viewBinding.descriptionText.also { view ->
                view.text = readText(resId = R.string.night_mode)
            }

            viewBinding.toggleSwitch.also { switch ->
                switch.isChecked = preference.isNightTheme

                switch.setOnCheckedChangeListener { _, isChecked ->
                    preference.isNightTheme = isChecked
                    setDefaultNightMode(MODE_NIGHT_YES.takeIf { isChecked } ?: MODE_NIGHT_NO)
                }
            }
        }.root
    }
}
