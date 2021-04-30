@file:SuppressLint("RestrictedApi")

/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bael.dads.library.instrumentation.fragment

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent.makeMainActivity
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.annotation.StyleRes
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import com.bael.dads.library.instrumentation.R
import com.bael.dads.library.instrumentation.activity.MainTestActivity

/**
 * launchFragmentInContainer from the androidx.fragment:fragment-testing library
 * is NOT possible to use right now as it uses a hardcoded Activity under the hood
 * which is not annotated with @AndroidEntryPoint.
 *
 * As a workaround, use this function that is equivalent. It requires you to add
 * [MainTestActivity] in the debug folder and include it in the debug styles.xml file
 * as can be found in this project.
 */
inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    context: Context,
    fragmentArgs: Bundle? = null,
    @NavigationRes graphResId: Int = -1,
    @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    crossinline action: Fragment.() -> Unit = {}
) {
    val startActivityIntent = makeMainActivity(
        ComponentName(context, MainTestActivity::class.java)
    ).putExtra(
        "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
        themeResId
    )

    ActivityScenario.launch<MainTestActivity>(startActivityIntent).onActivity { activity ->
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        ).also { fragment ->
            fragment.arguments = fragmentArgs

            if (graphResId == -1) return@also
            fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    val navigation = TestNavHostController(context)
                    navigation.setViewModelStore(ViewModelStore())
                    navigation.setGraph(graphResId)

                    setViewNavController(fragment.requireView(), navigation)
                }
            }
        }

        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        fragment.action()
    }
}
