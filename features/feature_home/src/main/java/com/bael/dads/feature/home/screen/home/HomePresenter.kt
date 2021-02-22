package com.bael.dads.feature.home.screen.home

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface HomePresenter {
    val queryFlow: StateFlow<String>
}
