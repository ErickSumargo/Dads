@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.presentation.sheet

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bael.dads.lib.presentation.ext.screenHeight
import com.bael.dads.lib.presentation.renderer.RendererInitializer
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import com.bael.dads.lib.threading.Thread
import com.google.android.material.bottomsheet.BottomSheetBehavior.from
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.bael.dads.lib.presentation.R as RPresentation
import com.google.android.material.R as RMaterial

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseSheet<VB : ViewBinding, R, E, VM : BaseViewModel<*, E>> :
    BottomSheetDialogFragment() {
    @Inject
    internal lateinit var rendererInitializer: RendererInitializer<R, VM>

    @Inject
    protected lateinit var thread: Thread

    abstract val fullHeight: Boolean

    protected abstract val viewModel: VM

    private val key: String
        get() = javaClass.name

    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initRenderer()
        observeEvent()

        _viewBinding = createView(inflater, container)
        return _viewBinding?.root
    }

    private fun initRenderer() {
        rendererInitializer.init(
            renderer = this as R,
            viewModel = viewModel
        )
    }

    private fun observeEvent() {
        viewModel.eventFlow
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = RESUMED
            )
            .onEach(::action)
            .launchIn(scope = viewLifecycleOwner.lifecycleScope)
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener(::adjustSheetHeight)

        return dialog
    }

    private fun adjustSheetHeight(dialog: DialogInterface) {
        val sheetLayout = (dialog as BottomSheetDialog)
            .findViewById<FrameLayout>(RMaterial.id.design_bottom_sheet)
        val sheetBehavior = from(sheetLayout!!)

        if (!fullHeight) {
            sheetLayout.also { layout ->
                layout.layoutParams.height = WRAP_CONTENT
            }
        } else {
            sheetLayout.also { layout ->
                layout.layoutParams.height = MATCH_PARENT
            }

            sheetBehavior.also { behavior ->
                behavior.peekHeight = screenHeight
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            onViewLoaded()
        }
    }

    abstract suspend fun onViewLoaded()

    abstract suspend fun action(event: E)

    fun show(fragmentManager: FragmentManager?) {
        fragmentManager ?: return
        show(fragmentManager, key)
    }

    override fun getTheme(): Int {
        return RPresentation.style.BottomSheetDialogTheme
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
