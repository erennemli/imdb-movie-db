package com.example.imdb.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.util.extension.lazyThreadSafetyNone
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : BaseViewModel, B: ViewDataBinding> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected val binder by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }

    @Suppress("UNCHECKED_CAST")
    private val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0] as Class<VM>

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(persistentViewModelClass) }
}