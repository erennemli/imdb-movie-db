package com.example.imdb.util.general

import android.graphics.Rect
import android.os.Handler
import android.os.SystemClock
import android.text.*
import android.text.method.TransformationMethod
import android.text.style.UpdateLayout
import android.view.View
import androidx.annotation.Nullable
import com.example.imdb.util.general.Constants.Common.DOT
import java.lang.ref.WeakReference

class PasswordTransformationMethodWithDelay : TransformationMethod, TextWatcher {

    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        if (source is Spannable) {
            val vr = source.getSpans(
                0, source.length,
                ViewReference::class.java
            )
            vr.indices.forEach { i -> source.removeSpan(vr[i]) }

            removeVisibleSpans(source)

            source.setSpan(
                ViewReference(view), 0, 0,
                Spannable.SPAN_POINT_POINT
            )
        }

        return PasswordCharSequence(source)
    }

    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
        // no-op
    }

    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (s is Spannable && s.isNotEmpty()) {
            val vr = s.getSpans(
                0, s.length,
                ViewReference::class.java
            )
            if (vr.isEmpty()) return

            var v: View? = null
            var i = 0
            while (v == null && i < vr.size) {
                v = vr[i].get()
                i++
            }

            if (v == null) return

            removeVisibleSpans(s)
            s.setSpan(
                Visible(s, this), start, start + count,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    override fun afterTextChanged(s: Editable) {}

    override fun onFocusChanged(
        view: View,
        sourceText: CharSequence,
        focused: Boolean,
        direction: Int,
        @Nullable previouslyFocusedRect: Rect?
    ) {
        if (!focused) if (sourceText is Spannable) removeVisibleSpans(sourceText)
    }

    inner class PasswordCharSequence(val mSource: CharSequence) : CharSequence, GetChars {

        override val length: Int
            get() = mSource.length

        override fun get(i: Int): Char {
            if (mSource is Spanned) {
                val sp = mSource

                var st = sp.getSpanStart(ACTIVE)
                var en = sp.getSpanEnd(ACTIVE)

                if (i in st until en) {
                    return mSource[i]
                }

                val visible = sp.getSpans(0, sp.length, Visible::class.java)

                visible.indices.forEach { a ->
                    if (sp.getSpanStart(visible[a].mTransformer) >= 0) {
                        st = sp.getSpanStart(visible[a])
                        en = sp.getSpanEnd(visible[a])

                        if (i in st until en) {
                            return mSource[i]
                        }
                    }
                }
            }

            return DOT
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            val buf = CharArray(endIndex - startIndex)

            getChars(startIndex, endIndex, buf, 0)
            return String(buf)
        }

        override fun getChars(start: Int, end: Int, dest: CharArray?, destoff: Int) {
            TextUtils.getChars(mSource, start, end, dest, destoff)

            var st = -1
            var en = -1
            var nVisible = 0
            var starts: IntArray? = null
            var ends: IntArray? = null

            if (mSource is Spanned) {
                val sp = mSource

                st = sp.getSpanStart(ACTIVE)
                en = sp.getSpanEnd(ACTIVE)

                val visible = sp.getSpans(0, sp.length, Visible::class.java)
                nVisible = visible.size
                starts = IntArray(nVisible)
                ends = IntArray(nVisible)

                (0 until nVisible).forEach { i ->
                    if (sp.getSpanStart(visible[i].mTransformer) >= 0) {
                        starts[i] = sp.getSpanStart(visible[i])
                        ends[i] = sp.getSpanEnd(visible[i])
                    }
                }
            }

            for (i in start until end) {
                if (i !in st until en) {
                    var visible = false

                    for (a in 0 until nVisible) {
                        if (i >= starts!![a] && i < ends!![a]) {
                            visible = true
                            break
                        }
                    }

                    if (!visible) {
                        dest?.get(i - start + destoff)?.let {
                            if (it.isDigit()) {
                                dest[i - start + destoff] = DOT
                            }
                        }
                    }
                }
            }
        }
    }

    private class Visible(
        private val mText: Spannable,
        internal val mTransformer: PasswordTransformationMethodWithDelay
    ) : Handler(), UpdateLayout, Runnable {

        init {
            postAtTime(this, SystemClock.uptimeMillis() + DELAY)
        }

        override fun run() {
            mText.removeSpan(this)
        }
    }

    private class ViewReference(v: View) : WeakReference<View>(v), NoCopySpan

    companion object {
        internal val ACTIVE: Any = NoCopySpan.Concrete()
        private var sInstance: PasswordTransformationMethodWithDelay? = null
        const val DELAY = 500L

        val instance: PasswordTransformationMethodWithDelay
            get() {
                if (sInstance != null)
                    return sInstance as PasswordTransformationMethodWithDelay

                sInstance = PasswordTransformationMethodWithDelay()
                return sInstance as PasswordTransformationMethodWithDelay
            }

        private fun removeVisibleSpans(sp: Spannable) {
            val old = sp.getSpans(0, sp.length, Visible::class.java)
            for (i in old.indices) {
                sp.removeSpan(old[i])
            }
        }
    }
}
