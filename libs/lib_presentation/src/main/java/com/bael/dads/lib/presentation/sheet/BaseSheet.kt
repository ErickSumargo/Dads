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
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bael.dads.lib.presentation.ext.screenHeight
import com.bael.dads.lib.presentation.renderer.RendererInitializer
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import com.bael.dads.lib.threading.Thread
import com.google.android.material.bottomsheet.BottomSheetBehavior.from
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject
import com.bael.dads.lib.presentation.R as RPresentation
import com.google.android.material.R as RMaterial

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseSheet<VB : ViewBinding, R, VM : BaseViewModel<*>> : BottomSheetDialogFragment() {
    @Inject
    internal lateinit var rendererInitializer: RendererInitializer<R, VM>

    @Inject
    internal lateinit var _viewModel: @JvmSuppressWildcards Lazy<VM>

    @Inject
    protected lateinit var thread: Thread

    protected abstract val fullHeight: Boolean

    private val key: String
        get() = javaClass.name

    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

    protected val viewModel: VM
        get() = _viewModel.value

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initRenderer()

        _viewBinding = createView(inflater, container)
        return _viewBinding?.root
    }

    private fun initRenderer() {
        rendererInitializer.init(
            renderer = this as R,
            viewModel = viewModel
        )
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
        lifecycleScope.launchWhenResumed {
            onViewLoaded()
        }
    }

    abstract suspend fun onViewLoaded()

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
