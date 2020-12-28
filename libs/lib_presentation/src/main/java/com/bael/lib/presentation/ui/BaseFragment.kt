@file:Suppress("UNCHECKED_CAST")

package com.bael.lib.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bael.lib.presentation.renderer.RendererFactory
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseFragment<VB : ViewBinding, C, VM, R : RendererFactory<C, VM>> : Fragment() {
    @Inject
    internal lateinit var renderer: R

    @Inject
    internal lateinit var viewModelDeferred: @JvmSuppressWildcards Lazy<VM>

    private var _viewBinding: VB? = null

    protected val viewBinding: VB get() = _viewBinding!!

    protected val viewModel: VM by lazy { viewModelDeferred.value }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        renderer.init(component = this as C, viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = bindView(inflater, container)
        return _viewBinding?.root
    }

    abstract fun bindView(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}
