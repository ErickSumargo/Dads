package com.bael.dads.library.presentation.widget.tab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bael.dads.library.presentation.databinding.ItemBottomTabBinding
import com.bael.dads.library.presentation.databinding.ItemBottomTabBinding.bind
import com.bael.dads.library.presentation.databinding.ItemBottomTabBinding.inflate
import com.bael.dads.library.presentation.widget.tab.data.BottomTab
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

/**
 * Created by ErickSumargo on 01/01/21.
 */

class BottomTabAdapter(
    private val tabLayout: TabLayout,
    private val onPageSelectedListener: (Int) -> Unit = {}
) : OnPageChangeCallback() {
    private val context: Context
        get() = tabLayout.context

    private val tabsData: MutableList<BottomTab> = mutableListOf()

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)

        selectTab(position)
        onPageSelectedListener.invoke(position)
    }

    fun configureTab(tab: Tab, data: BottomTab) {
        configureTab(tab)
        storeTabData(data)
        renderDefaultTab(tab, data)
    }

    private fun configureTab(tab: Tab) {
        tab.customView = createTabView()
        tab.view.background = null
    }

    private fun storeTabData(data: BottomTab) {
        tabsData.add(data)
    }

    private fun selectTab(position: Int) {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)!!
            val viewBinding = bindTabView(tab)

            renderTab(
                viewBinding,
                data = tabsData[i],
                isSelected = i == position
            )
        }
    }

    private fun createTabView(): View {
        val viewBinding = inflate(LayoutInflater.from(context))
        return viewBinding.root
    }

    private fun bindTabView(tab: Tab): ItemBottomTabBinding {
        return bind(tab.customView ?: tab.view)
    }

    private fun renderDefaultTab(tab: Tab, data: BottomTab) {
        val viewBinding = bindTabView(tab)
        renderTab(viewBinding, data, isSelected = false)
    }

    private fun renderTab(
        viewBinding: ItemBottomTabBinding,
        data: BottomTab,
        isSelected: Boolean
    ) {
        viewBinding.root.also { layout ->
            if (isSelected) {
                layout.transitionToEnd()
            } else {
                layout.transitionToStart()
            }
        }

        viewBinding.imageIcon.also { icon ->
            icon.setImageDrawable(data.icon)
        }

        viewBinding.nameText.also { view ->
            view.text = data.name
        }
    }
}
