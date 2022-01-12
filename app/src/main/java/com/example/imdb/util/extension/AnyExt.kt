package com.example.imdb.util.extension

fun <T, R> safeLet(p1: T?, p2: R?, block: (T, R) -> Unit) {
    if (p1 != null && p2 != null) block(p1, p2)
}