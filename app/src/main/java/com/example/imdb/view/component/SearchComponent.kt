package com.example.imdb.view.component

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.fragment.app.FragmentActivity
import com.example.imdb.R
import com.example.imdb.databinding.ComponentSearchBinding
import com.example.imdb.util.extension.hide
import com.example.imdb.util.extension.init
import com.example.imdb.util.extension.listenQueryChanges
import com.example.imdb.util.extension.setRoundedBackground
import com.example.imdb.util.extension.show
import com.example.imdb.util.general.BindingComponent
import com.example.imdb.util.general.Constants.Common.ZERO
import com.example.imdb.util.general.ViewEntity

class SearchComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = ZERO
) : FrameLayout(context, attributeSet, defStyleAttr),
    BindingComponent<ComponentSearchBinding, SearchComponent.Entity> {

    override val binding: ComponentSearchBinding = ComponentSearchBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    @ColorInt
    private var _viewBackgroundColor: Int = Color.BLACK

    var rawHint: CharSequence?
        get() = binding.searchView.queryHint
        set(value) {
            binding.searchView.queryHint = value
            binding.textViewHint.text = value
        }

    var viewBackgroundColor: Int
        @ColorInt get() = _viewBackgroundColor
        set(@ColorInt value) {
            _viewBackgroundColor = value
            setRoundedBackground(ROUND, value)
        }

    var onQueryTextChange: ((query: String?) -> Unit)? = null

    var onSearchClickListener: ((isFocused: Boolean) -> Unit)? = null

    init {
        obtainStyledAttributes(attributeSet, defStyleAttr)
    }

    fun initializedSearchView(context: FragmentActivity) {
        binding.searchView.apply {
            init(
                context = context,
                queryHint = rawHint
            ).listenQueryChanges(
                onQueryTextChange = { newText ->
                    onQueryTextChange?.invoke(newText)
                    true
                }, onQueryTextSubmit = {
                    false
                }
            )

            setOnClickListener {
                isIconified = false
                binding.textViewHint.hide()
                onSearchClickListener?.invoke(true)
            }

            setOnSearchClickListener {
                binding.textViewHint.hide()
                onSearchClickListener?.invoke(true)
            }

            setOnCloseListener {
                binding.textViewHint.show()
                onSearchClickListener?.invoke(false)
                false
            }
        }
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SearchComponent,
            defStyleAttr,
            0
        )

        try {
            with(typedArray) {

                rawHint = getString(
                    R.styleable.SearchComponent_rawHint
                )

                viewBackgroundColor = getColor(
                    R.styleable.SearchComponent_viewBackgroundColor,
                    viewBackgroundColor
                )
            }
        } catch (e: Exception) {
            // ignore
        } finally {
            typedArray.recycle()
        }
    }

    override fun setup(viewEntity: Entity?) {
        viewEntity?.let {
            // no-op
        }
    }

    data class Entity(
        val id: Int?
    ) : ViewEntity()

    companion object {
        private const val ROUND = 0f
    }
}
