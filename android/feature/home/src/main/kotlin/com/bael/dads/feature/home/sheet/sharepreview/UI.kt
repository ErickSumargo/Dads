package com.bael.dads.feature.home.sheet.sharepreview

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_STREAM
import android.content.Intent.EXTRA_TITLE
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.createChooser
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat.PNG
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider.getUriForFile
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.databinding.SheetSharePreviewBinding
import com.bael.dads.feature.home.databinding.SheetSharePreviewBinding.inflate
import com.bael.dads.library.presentation.ext.toRichText
import com.bael.dads.library.presentation.sheet.BaseSheet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseSheet<SheetSharePreviewBinding, Renderer, Event, ViewModel>(),
    Renderer {
    override val fullHeight: Boolean = false

    override val viewModel: ViewModel by viewModels()

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SheetSharePreviewBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded() {
        viewModel.receiveDadJoke()
    }

    override suspend fun action(event: Event) {}

    override fun renderPreview(dadJoke: DadJoke?) {
        dadJoke ?: return

        viewBinding.setupText.also { view ->
            view.text = dadJoke.setup.toRichText()
        }

        viewBinding.punchlineText.also { view ->
            view.text = dadJoke.punchline.toRichText()
        }

        viewBinding.shareText.also { view ->
            view.setOnClickListener {
                shareDadJoke(
                    view = viewBinding.contentLayout,
                    dadJoke = dadJoke
                )
            }
        }
    }

    private fun shareDadJoke(view: View, dadJoke: DadJoke) {
        lifecycleScope.launch(context = thread.io) {
            val uri = uriFromViewData(view, dadJoke)
            val data = Intent().apply {
                data = uri
                action = ACTION_SEND
                flags = FLAG_GRANT_READ_URI_PERMISSION

                putExtra(EXTRA_TITLE, "Dads")
                putExtra(EXTRA_STREAM, uri)
            }

            withContext(context = thread.main) {
                val intent = createChooser(data, null)
                startActivityForResult(intent, SHARE_INTENT_REQUEST_CODE)
            }
        }
    }

    private fun uriFromViewData(view: View, dadJoke: DadJoke): Uri {
        val bitmap = bitmapFromView(view)

        val folder = File(context?.cacheDir, "images")
        folder.mkdirs()

        val file = File(folder, "image_${dadJoke.id}.png")
        FileOutputStream(file).use { stream ->
            bitmap.compress(PNG, 100, stream)
            stream.flush()
        }

        return getUriForFile(requireContext(), "${context?.packageName}.provider", file)
    }

    private fun bitmapFromView(view: View): Bitmap {
        val bitmap = createBitmap(view.width, view.height, ARGB_8888)
        val canvas = Canvas(bitmap)

        view.draw(canvas)

        return bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SHARE_INTENT_REQUEST_CODE -> dismiss()
        }
    }

    private companion object {
        const val SHARE_INTENT_REQUEST_CODE: Int = 1001
    }
}
