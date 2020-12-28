package com.bael.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bael.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also { viewBinding ->
            setContentView(viewBinding.root)
        }
    }
}
