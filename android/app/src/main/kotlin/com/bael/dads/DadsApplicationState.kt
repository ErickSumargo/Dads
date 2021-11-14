package com.bael.dads

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by ErickSumargo on 01/11/21.
 */

typealias SheetContent = @Composable (() -> Unit)

@ExperimentalMaterialApi
@Composable
fun rememberDadsState(
    bottomSheetState: ModalBottomSheetState,
    navController: NavHostController,
    coroutineScope: CoroutineScope
) = remember {
    DadsState(
        bottomSheetState,
        navController,
        coroutineScope
    )
}

@ExperimentalMaterialApi
class DadsState(
    val bottomSheetState: ModalBottomSheetState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
    var sheetContent: SheetContent? by mutableStateOf(null)

    fun showBottomSheet(content: SheetContent) {
        sheetContent = content

        coroutineScope.launch {
            bottomSheetState.show()
        }
    }
}
