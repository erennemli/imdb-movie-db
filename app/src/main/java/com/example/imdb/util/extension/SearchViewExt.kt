package com.example.imdb.util.extension

import android.app.Activity
import android.app.SearchManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

fun SearchView.init(
    context: Activity? = null,
    queryHint: CharSequence? = null
): SearchView {
    val searchManager = context?.getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
    this.queryHint = queryHint
    setSearchableInfo(searchManager.getSearchableInfo(context.componentName))
    return this
}

inline fun SearchView.listenQueryChanges(
    crossinline onQueryTextSubmit: (query: String?) -> Boolean,
    crossinline onQueryTextChange: (newText: String?) -> Boolean
) {
    val listener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean = onQueryTextSubmit.invoke(query)

        override fun onQueryTextChange(newText: String?): Boolean =
            onQueryTextChange.invoke(newText)
    }
    setOnQueryTextListener(listener)
}