package com.bael.dads.navigation

import android.content.Context
import android.content.Intent
import com.bael.dads.activity.MainActivity
import com.bael.dads.lib.navigation.HomeNavigation
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeNavigationImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeNavigation {

    override fun getMainActivityIntent(): Intent {
        return Intent(context, MainActivity::class.java)
    }
}
