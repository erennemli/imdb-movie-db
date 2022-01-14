package com.example.imdb.view.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.example.imdb.R
import com.example.imdb.databinding.ViewDefaultInputBinding
import com.example.imdb.util.extension.clear
import com.example.imdb.util.extension.hidePassword
import com.example.imdb.util.extension.loadColor
import com.example.imdb.util.extension.loadDrawable
import com.example.imdb.util.extension.showPassword
import com.example.imdb.util.general.BindingView
import com.example.imdb.util.general.Constants.Common.MAX_INPUT_LENGTH
import com.example.imdb.util.general.DefaultTextWatcher
import com.google.android.material.textfield.TextInputLayout

open class DefaultInput : BindingView<ViewDefaultInputBinding>,
    BaseInput<CharSequence> {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        obtainStyledAttributes(attrs, R.attr.DefaultInputStyle)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        obtainStyledAttributes(attrs, defStyleAttr)
    }

    var focusChangeListener: ((hasFocus: Boolean) -> Unit)? = null

    final override val binding = ViewDefaultInputBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    override fun onFocusOut() {
        super.onFocusOut()
        binding.textInputEditText.isCursorVisible = false
    }

    override var isRequired: Boolean = true

    open val defaultTextWatcher = object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isValid()
            notifyInputChangeListeners()
        }
    }

    private val defaultFocusListener = OnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            onFocusOut()
        } else {
            binding.textInputEditText.isCursorVisible = true
        }
        focusChangeListener?.invoke(hasFocus)
    }

    override fun onClear() {
        super.onClear()
        binding.textInputEditText.clearFocus()
        binding.textInputEditText.isCursorVisible = false
    }

    // attribute defaults
    private var _inputType: Int =
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
    private var _textMaxLength = MAX_INPUT_LENGTH
    private var _editable = true
    private var _hasClearButton = false
    private var _hasPasswordToggle = false
    private var _iconEnd: Drawable? = null

    @ColorInt
    private var _viewBoxBackgroundColor: Int = context.loadColor(R.color.main)

    @ColorInt
    private var _systemEndIconColor: Int = context.loadColor(R.color.white)

    private var _toggleState: Boolean = false

    val toggleState: Boolean
        get() = _toggleState

    var editable: Boolean
        get() = _editable
        set(value) {
            _editable = value
            applyInputEditable(value)
        }

    var rawText: CharSequence?
        get() = binding.textInputEditText.text
        set(value) {
            binding.textInputEditText.setText(value)
        }

    var rawHint: CharSequence?
        get() = binding.textInputEditText.hint
        set(value) {
            binding.textInputLayout.isHintEnabled = !value.isNullOrEmpty()
            binding.textInputLayout.hint = value
        }

    var rawHelper: CharSequence?
        get() = binding.textInputLayout.helperText
        set(value) {
            binding.textInputLayout.isHelperTextEnabled = !value.isNullOrEmpty()
            binding.textInputLayout.helperText = value
        }

    var iconEnd: Drawable?
        get() = _iconEnd
        set(value) {
            _iconEnd = value
            applyEndIcon()
        }

    var textMaxLength: Int
        get() = _textMaxLength
        set(value) {
            binding.textInputEditText.filters = arrayOf(InputFilter.LengthFilter(value))
            _textMaxLength = value
        }

    var rawTextColor: Int
        @ColorInt get() = binding.textInputEditText.currentTextColor
        set(@ColorInt value) {
            binding.textInputEditText.setTextColor(value)
        }

    var rawHintColor: Int
        @ColorInt get() = binding.textInputLayout.defaultHintTextColor?.defaultColor ?: 0
        set(@ColorInt value) {
            binding.textInputLayout.defaultHintTextColor = ColorStateList.valueOf(value)
        }

    var rawHelperColor: Int
        @ColorInt get() = binding.textInputLayout.helperTextCurrentTextColor
        set(@ColorInt value) {
            binding.textInputLayout.setHelperTextColor(ColorStateList.valueOf(value))
        }

    var errorColor: Int
        @ColorInt get() = binding.textInputLayout.errorCurrentTextColors
        set(@ColorInt value) {
            binding.textInputLayout.setErrorTextColor(ColorStateList.valueOf(value))
        }

    var rawTextSize: Float
        @Dimension get() = binding.textInputEditText.textSize
        set(@Dimension value) {
            binding.textInputEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    var viewFocusableInTouchMode: Boolean
        get() = binding.textInputEditText.isFocusableInTouchMode
        set(value) {
            binding.textInputEditText.isFocusableInTouchMode = value
        }

    protected var rawInputType: Int
        get() = _inputType
        set(value) {
            _inputType = value
            binding.textInputEditText.inputType = value
        }

    var hasPasswordToggle: Boolean
        get() = _hasPasswordToggle
        set(value) {
            if (value) {
                binding.textInputEditText.showPassword()
                applyPasswordToggle()
            }
        }

    var hasClearButton: Boolean
        get() = _hasClearButton
        set(value) {
            _hasClearButton = value
            if (value) {
                binding.textInputLayout.apply {
                    endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                }
            }
        }

    var systemEndIconColor: Int
        @ColorInt get() = _systemEndIconColor
        set(value) {
            _systemEndIconColor = value
            if (_hasClearButton or _hasPasswordToggle) {
                binding.textInputLayout.apply {
                    setEndIconTintMode(PorterDuff.Mode.MULTIPLY)
                    setEndIconTintList(ColorStateList.valueOf(value))
                }
            }
        }

    var viewBoxBackgroundColor: Int
        @ColorInt get() = _viewBoxBackgroundColor
        set(value) {
            _viewBoxBackgroundColor = value
        }

    // listeners
    var onEndIconClickListener: (() -> Unit)? = null
    var onEndIconToggleListener: ((bool: Boolean) -> Unit)? = null

    init {
        binding.apply {
            textInputEditText.apply {
                filters = arrayOf(InputFilter.LengthFilter(textMaxLength))
            }

            textInputLayout.apply {
                isStartIconVisible = false
                textInputLayout.isEndIconVisible = false
            }
        }
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DefaultInput,
            defStyleAttr,
            0
        )
        val typedValue = TypedValue()
        context.theme.resolveAttribute(
            R.attr.appBoxBackgroundColor,
            typedValue,
            true
        )

        try {
            with(typedArray) {
                isRequired = getBoolean(
                    R.styleable.DefaultInput_required,
                    isRequired
                )

                editable = getBoolean(
                    R.styleable.DefaultInput_editable,
                    editable
                )

                rawText = getString(
                    R.styleable.DefaultInput_rawText
                )

                rawHint = getString(
                    R.styleable.DefaultInput_rawHint
                )

                rawHelper = getString(
                    R.styleable.DefaultInput_rawHelper
                )

                iconEnd = getDrawable(
                    R.styleable.DefaultInput_iconEnd
                )

                rawTextColor = getColor(
                    R.styleable.DefaultInput_rawTextColor,
                    rawTextColor
                )

                rawHintColor = getColor(
                    R.styleable.DefaultInput_rawHintColor,
                    rawHintColor
                )

                rawHelperColor = getColor(
                    R.styleable.DefaultInput_rawHelperColor,
                    rawHelperColor
                )

                errorColor = getColor(
                    R.styleable.DefaultInput_errorColor,
                    errorColor
                )

                rawTextSize = getDimension(
                    R.styleable.DefaultInput_rawTextSize,
                    rawTextSize
                )

                inputMatches = getResourceId(
                    R.styleable.DefaultInput_matches,
                    inputMatches
                )

                textMaxLength = getInt(
                    R.styleable.DefaultInput_textMaxLength,
                    textMaxLength
                )

                viewFocusableInTouchMode = getBoolean(
                    R.styleable.DefaultInput_viewFocusable,
                    viewFocusableInTouchMode
                )

                rawInputType = getInt(
                    R.styleable.DefaultInput_android_inputType, EditorInfo.TYPE_NULL
                )

                hasClearButton = getBoolean(
                    R.styleable.DefaultInput_hasClearButton, hasClearButton
                )

                hasPasswordToggle = getBoolean(
                    R.styleable.DefaultInput_hasPasswordToggle, hasPasswordToggle
                )

                systemEndIconColor = getColor(
                    R.styleable.DefaultInput_systemEndIconColor,
                    systemEndIconColor
                )

                viewBoxBackgroundColor = typedValue.data
            }
        } catch (e: Exception) {
            // ignore
        } finally {
            typedArray.recycle()
        }
    }

    private fun applyEndIcon() {
        iconEnd?.let { icon ->
            binding.textInputLayout.apply {
                endIconMode = TextInputLayout.END_ICON_CUSTOM
                isEndIconVisible = true
                isEndIconCheckable = true
                endIconDrawable = icon
                setEndIconOnClickListener {
                    onEndIconClickListener?.invoke()
                }
            }
        }
    }

    private fun applyInputEditable(value: Boolean) {
        binding.textInputEditText.isEnabled = value
    }

    private fun applyPasswordToggle() {
        binding.apply {
            textInputLayout.apply {
                endIconMode = TextInputLayout.END_ICON_CUSTOM
                endIconDrawable = context.loadDrawable(R.drawable.ic_pass_enabled)
                setEndIconTintMode(PorterDuff.Mode.MULTIPLY)
                applyPasswordToggleListeners()
            }
        }
    }

    private fun applyPasswordToggleListeners() {
        binding.textInputLayout.apply {
            setEndIconOnClickListener {
                _toggleState = _toggleState.not()
                onEndIconToggleListener?.invoke(_toggleState)
                if (_toggleState) {
                    endIconDrawable = context.loadDrawable(R.drawable.ic_pass_disabled)
                    binding.textInputEditText.hidePassword()
                } else {
                    endIconDrawable = context.loadDrawable(R.drawable.ic_pass_enabled)
                    binding.textInputEditText.showPassword()
                }

                binding.textInputEditText.apply {
                    text?.let {
                        setSelection(it.length)
                    }
                }
            }
        }
    }

    override fun invoke(): CharSequence? = binding.textInputEditText.text

    override fun hasNoValue(): Boolean = invoke().isNullOrEmpty()

    override fun setInputValue(value: CharSequence?) = binding.textInputEditText.setText(value)

    override fun clearInputValue() {
        binding.textInputEditText.clear()
        default()
    }

    override fun default() {
        super.default()
        binding.textInputLayout.isErrorEnabled = false
    }

    override fun success() {
        super.success()
        binding.textInputLayout.isErrorEnabled = false
    }

    override fun error(errorRes: Int) {
        super.error(errorRes)
        binding.textInputLayout.isErrorEnabled = true
        binding.textInputLayout.error = context.resources.getString(errorRes)
    }

    fun clearError() {
        binding.textInputLayout.isErrorEnabled = false
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.textInputEditText.addTextChangedListener(defaultTextWatcher)
        binding.textInputEditText.onFocusChangeListener = defaultFocusListener
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding.textInputEditText.removeTextChangedListener(defaultTextWatcher)
        binding.textInputEditText.onFocusChangeListener = null
    }
}