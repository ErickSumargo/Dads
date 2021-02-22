@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bael.dads.lib.data.ext.invoke
import com.bael.dads.lib.presentation.renderer.RendererInitializer
import com.bael.dads.lib.presentation.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseFragment<VB : ViewBinding, R, VM : BaseViewModel<*>> : Fragment() {
    @Inject
    internal lateinit var rendererInitializer: RendererInitializer<R, VM>

    @Inject
    protected lateinit var viewModel: @JvmSuppressWildcards Lazy<VM>

    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

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
            viewModel = viewModel()
        )
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            onViewLoaded(savedInstanceState)
        }
    }

    abstract suspend fun onViewLoaded(savedInstanceState: Bundle?)

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun back() {
        findNavController().navigateUp()
    }

    protected fun showMessage(message: String?) {
        message ?: return
        Snackbar.make(viewBinding.root, message, LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
