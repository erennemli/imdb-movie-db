package com.example.imdb.util.general

import androidx.viewbinding.ViewBinding

interface BindingComponent<VB : ViewBinding, T : ViewEntity> : Component<T> {
    val binding: VB
}