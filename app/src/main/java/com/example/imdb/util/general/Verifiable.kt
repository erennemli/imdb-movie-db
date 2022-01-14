package com.example.imdb.util.general

interface Verifiable<T> {

    var isRequired: Boolean

    fun addValidators(vararg validator: Validator<T>)

    fun isValid(): Boolean

    fun addValidationChangeListener(listener: (Boolean) -> Unit)

    fun clearValidators()

    fun removeValidator(validator: Validator<T>)

    fun clearValidationChangeListeners()

    fun removeValidationChangeListener(validationChangeListener: (Boolean) -> Unit)

    fun validated(): Boolean
}
