package com.example.imdb.view.component

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.imdb.util.general.Constants.Common.INVALID_RES
import com.example.imdb.util.general.InputHolder
import com.example.imdb.util.general.Validator
import com.example.imdb.util.general.Verifiable

abstract class BaseInput<T> : FrameLayout, InputHolder<T?>, Verifiable<T?> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    open fun onClear() {
        // np-op
    }

    private val validators = mutableListOf<Validator<T?>>()
    private val validationChangeListeners = mutableListOf<(Boolean) -> Unit?>()
    private val inputChangeListeners = mutableListOf<(T?) -> Unit?>()
    private var validated: Boolean = false

    @IdRes
    var inputMatches: Int = INVALID_RES

    open fun onFocusOut() {
        if (isShown) {
            if (!isRequired && hasNoValue()) {
                success()
                return
            }

            if (!hasNoValue()) {
                validators.forEach { validator ->
                    if (!validator.validate(invoke())) {
                        error(validator.errorRes)
                    }
                }
            }
        }
    }

    open fun error(@StringRes errorRes: Int) {
        // no-op
    }

    open fun default() {
        this.validated = false
        notifyValidationChangeListeners()
    }

    open fun success() {
        this.validated = true
        notifyValidationChangeListeners()
    }

    override fun addInputChangeListener(listener: (T?) -> Unit) {
        inputChangeListeners.add(listener)
    }

    override fun removeInputChangeListener(listener: (T?) -> Unit) {
        inputChangeListeners.remove(listener)
    }

    override fun removeAllInputChangeListeners() {
        inputChangeListeners.clear()
    }

    final override fun addValidators(vararg validator: Validator<T?>) {
        validators.addAll(validator)
    }

    override fun isValid(): Boolean {
        if (!isRequired && hasNoValue()) {
            success()
            notifyValidationChangeListeners()
            return this.validated
        }

        validators.forEach { validator ->
            if (!validator.validate(invoke())) {
                this.validated = false
                notifyValidationChangeListeners()
                return this.validated
            }
        }

        success()
        return this.validated
    }

    override fun addValidationChangeListener(listener: (Boolean) -> Unit) {
        validationChangeListeners.add(listener)
        isValid()
    }

    override fun clearValidators() {
        validators.clear()
    }

    override fun removeValidator(validator: Validator<T?>) {
        validators.remove(validator)
    }

    override fun clearValidationChangeListeners() {
        validationChangeListeners.clear()
    }

    override fun removeValidationChangeListener(validationChangeListener: (Boolean) -> Unit) {
        validationChangeListeners.remove(validationChangeListener)
    }

    override fun validated(): Boolean = this.validated

    private fun notifyValidationChangeListeners() {
        validationChangeListeners.forEach { listener ->
            listener.invoke(this.validated)
        }
    }

    protected fun notifyInputChangeListeners() {
        inputChangeListeners.forEach { listener ->
            listener.invoke(invoke())
        }
    }
}
