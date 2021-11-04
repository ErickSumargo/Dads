//package com.bael.dads.feature.home.screen.sharepreview
//
//import androidx.lifecycle.SavedStateHandle
//import com.bael.dads.library.presentation.ext.reduce
//import com.bael.dads.library.presentation.viewmodel.BaseViewModel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
///**
// * Created by ErickSumargo on 01/11/21.
// */
//
//@HiltViewModel
//internal class ViewModel @Inject constructor(
//    initState: State,
//    savedStateHandle: SavedStateHandle
//) : BaseViewModel<State, Event>(initState, savedStateHandle) {
//
//    fun receiveDadJoke() {
//        val newState = state.reduce {
//            copy(dadJoke = savedStateHandle.get("dadJoke"))
//        }
//        render(newState)
//    }
//}
