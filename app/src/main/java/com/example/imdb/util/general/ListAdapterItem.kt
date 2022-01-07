package com.example.imdb.util.general

interface ListAdapterItem {
    val id: Int

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}