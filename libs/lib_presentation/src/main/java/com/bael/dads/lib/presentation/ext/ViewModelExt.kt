package com.bael.dads.lib.presentation.ext

import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
operator fun <VM : BaseViewModel<*>> Lazy<VM>.invoke(): VM = value
