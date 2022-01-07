package com.example.imdb.scene.main

import android.os.Bundle
import com.example.imdb.R
import com.example.imdb.base.BaseActivity
import com.example.imdb.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.lifecycleOwner = this
    }

}