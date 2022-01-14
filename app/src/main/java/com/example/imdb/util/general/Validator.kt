package com.example.imdb.util.general

interface Validator<T> {

    var errorRes: Int

    fun validate(input: T?): Boolean
}
