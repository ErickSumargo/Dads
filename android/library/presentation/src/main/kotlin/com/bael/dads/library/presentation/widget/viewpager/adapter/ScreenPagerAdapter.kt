package com.bael.dads.library.presentation.widget.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by ErickSumargo on 01/01/21.
 */

class ScreenPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val screens: List<() -> Fragment>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = screens.size

    override fun createFragment(position: Int): Fragment {
        return screens[position]()
    }
}
