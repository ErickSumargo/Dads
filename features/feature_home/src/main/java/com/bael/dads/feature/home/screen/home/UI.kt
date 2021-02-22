package com.bael.dads.feature.home.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bael.dads.feature.home.databinding.ScreenHomeBinding
import com.bael.dads.feature.home.databinding.ScreenHomeBinding.inflate
import com.bael.dads.lib.data.ext.invoke
import com.bael.dads.lib.presentation.ext.hideSoftKeyboard
import com.bael.dads.lib.presentation.ext.showSoftKeyboard
import com.bael.dads.lib.presentation.fragment.BaseFragment
import com.bael.dads.lib.presentation.widget.listener.OnTextChangedListener
import com.bael.dads.lib.presentation.widget.tab.adapter.BottomTabAdapter
import com.bael.dads.lib.presentation.widget.tab.data.BottomTab
import com.bael.dads.lib.presentation.widget.viewpager.adapter.ScreenPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.bael.dads.feature.home.sheet.settings.UI as SettingsSheet

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseFragment<ScreenHomeBinding, Renderer, ViewModel>(),
    Renderer {
    @Inject
    lateinit var pagerScreens: @JvmSuppressWildcards List<() -> Fragment>

    @Inject
    lateinit var tabsData: @JvmSuppressWildcards List<BottomTab>

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ScreenHomeBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded(savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        val bottomTabAdapter = BottomTabAdapter(
            tabLayout = viewBinding.tabLayout,
            onPageSelectedListener = ::adjustNavBar
        )

        viewBinding.viewPager.also { pager ->
            pager.adapter = ScreenPagerAdapter(
                fragmentManager = childFragmentManager,
                lifecycle = viewLifecycleOwner.lifecycle,
                screens = pagerScreens
            )
            pager.registerOnPageChangeCallback(bottomTabAdapter)
        }

        viewBinding.toolbarLayout.also { layout ->
            layout.queryInput.also { input ->
                callbackFlow {
                    val onTextChangedListener = OnTextChangedListener(::offer)

                    input.addTextChangedListener(onTextChangedListener)
                    awaitClose { input.removeTextChangedListener(onTextChangedListener) }
                }
                    .debounce(timeoutMillis = 500L)
                    .onEach(viewModel()::submitQuery)
                    .launchIn(scope = viewLifecycleOwner.lifecycleScope)
            }

            layout.clearIcon.also { icon ->
                icon.setOnClickListener {
                    icon.visibility = GONE

                    layout.logoIcon.also { icon ->
                        icon.visibility = VISIBLE
                    }

                    layout.queryInput.also { input ->
                        input.text.clear()
                        input.visibility = GONE

                        input.clearFocus()
                        input.hideSoftKeyboard()
                    }

                    layout.searchIcon.also { icon ->
                        icon.visibility = VISIBLE
                    }
                }
            }

            layout.searchIcon.also { icon ->
                icon.setOnClickListener {
                    icon.visibility = GONE

                    layout.logoIcon.also { icon ->
                        icon.visibility = GONE
                    }

                    layout.queryInput.also { input ->
                        input.visibility = VISIBLE

                        input.requestFocus()
                        input.showSoftKeyboard()
                    }

                    layout.clearIcon.also { icon ->
                        icon.visibility = VISIBLE
                    }
                }
            }

            layout.settingsIcon.also { icon ->
                icon.setOnClickListener {
                    showSettingsSheet()
                }
            }
        }

        TabLayoutMediator(
            viewBinding.tabLayout,
            viewBinding.viewPager
        ) { tab, position ->
            bottomTabAdapter.configureTab(tab, data = tabsData[position])
        }.attach()
    }

    private fun adjustNavBar(position: Int) {
        when (position) {
            0 -> {
                viewBinding.toolbarLayout.also { layout ->
                    layout.logoIcon.also { icon ->
                        icon.visibility = VISIBLE
                    }

                    layout.queryInput.also { input ->
                        input.visibility = GONE

                        input.clearFocus()
                        input.hideSoftKeyboard()
                    }

                    layout.clearIcon.also { icon ->
                        icon.visibility = GONE
                    }

                    layout.searchIcon.also { icon ->
                        icon.visibility = GONE
                    }
                }
            }
            1 -> {
                viewBinding.toolbarLayout.also { layout ->
                    layout.logoIcon.also { icon ->
                        icon.visibility = VISIBLE
                    }

                    layout.queryInput.also { input ->
                        input.visibility = GONE

                        input.clearFocus()
                        input.hideSoftKeyboard()
                    }

                    layout.clearIcon.also { icon ->
                        icon.visibility = GONE
                    }

                    layout.searchIcon.also { icon ->
                        icon.visibility = VISIBLE
                    }
                }
            }
        }
    }

    private fun showSettingsSheet() {
        SettingsSheet().also { sheet ->
            sheet.show(fragmentManager = activity?.supportFragmentManager)
        }
    }
}
