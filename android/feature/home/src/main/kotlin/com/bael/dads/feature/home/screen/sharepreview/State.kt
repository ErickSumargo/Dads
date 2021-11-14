package com.bael.dads.feature.home.screen.sharepreview

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.content.FileProvider
import com.bael.dads.domain.home.model.DadJoke
import java.io.File
import java.io.FileOutputStream

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun rememberSharePreviewState(dadJoke: DadJoke) =
    remember(dadJoke) { SharePreviewState(dadJoke) }

internal class SharePreviewState(val dadJoke: DadJoke) {
    fun share(context: Context, fileName: String, bitmap: Bitmap) {
        val shareData = createShareData(context, fileName, bitmap)
        val intent = Intent.createChooser(shareData, null)

        context.startActivity(intent)
    }

    private fun createShareData(context: Context, fileName: String, bitmap: Bitmap): Intent {
        val uri = bitmap.toUri(fileName, context)
        return Intent().apply {
            data = uri
            action = Intent.ACTION_SEND
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

            putExtra(Intent.EXTRA_TITLE, "Dads")
            putExtra(Intent.EXTRA_STREAM, uri)
        }
    }

    private fun Bitmap.toUri(fileName: String, context: Context): Uri {
        val folder = File(context.cacheDir, "images")
        folder.mkdirs()

        val file = File(folder, fileName)
        FileOutputStream(file).use { stream ->
            compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
        }

        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }
}
