package com.example.imdb.util.general

interface Component<T : ViewEntity> {
    fun setup(viewEntity: T?)
}