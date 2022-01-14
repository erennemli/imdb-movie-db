package com.example.imdb.util.general

interface InputHolder<T> {

    operator fun invoke(): T?

    fun hasNoValue(): Boolean

    fun setInputValue(value: T?)

    fun clearInputValue()

    fun addInputChangeListener(listener: (T) -> Unit)

    fun removeInputChangeListener(listener: (T) -> Unit)

    fun removeAllInputChangeListeners()
}