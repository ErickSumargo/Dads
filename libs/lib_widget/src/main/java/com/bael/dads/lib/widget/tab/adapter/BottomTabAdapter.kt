package com.bael.dads.lib.widget.tab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bael.dads.lib.widget.databinding.BottomTabLayoutBinding
import com.bael.dads.lib.widget.databinding.BottomTabLayoutBinding.bind
import com.bael.dads.lib.widget.databinding.BottomTabLayoutBinding.inflate
import com.bael.dads.lib.widget.tab.state.BottomTab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

/**
 * Created by ErickSumargo on 01/01/21.
 */

class BottomTabAdapter(private val tabLayout: TabLayout) : OnPageChangeCallback() {
    val onPageSelectedListener: (Int) -> Unit = {}

    private val context: Context
        get() = tabLayout.context

    private val states: MutableList<BottomTab> = mutableListOf()

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)

        selectTab(position)
        onPageSelectedListener.invoke(position)
    }

    fun configureTab(tab: Tab, state: BottomTab) {
        tab.customView = createTabView()
        tab.view.background = null

        states.add(state)

        val viewBinding = bindTabView(tab)
        renderTab(viewBinding, state, isSelected = false)
    }

    private fun selectTab(position: Int) {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)!!
            val viewBinding = bindTabView(tab)

            renderTab(viewBinding, states[i], isSelected = i == position)
        }
    }

    private fun createTabView(): View {
        val viewBinding = inflate(LayoutInflater.from(context))
        return viewBinding.root
    }

    private fun bindTabView(tab: Tab): BottomTabLayoutBinding {
        return bind(tab.customView ?: tab.view)
    }

    private fun renderTab(
        viewBinding: BottomTabLayoutBinding,
        state: BottomTab,
        isSelected: Boolean
    ) {
        viewBinding.root.also { layout ->
            if (isSelected) {
                layout.transitionToEnd()
            } else {
                layout.transitionToStart()
            }
        }

        viewBinding.icon.also { icon ->
            icon.setImageDrawable(state.icon)
        }

        viewBinding.name.also { label ->
            label.text = state.name
        }
    }
}
