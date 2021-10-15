package com.efedaniel.ulesson.common.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.efedaniel.ulesson.R
import com.efedaniel.ulesson.extensions.hide

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.empty_view, this)

        context.withStyledAttributes(attrs, R.styleable.EmptyView) {
            findViewById<TextView>(R.id.emptyTextTitle).text = getString(R.styleable.EmptyView_emptyTitle)
            findViewById<TextView>(R.id.emptyTextDescription).text = getString(R.styleable.EmptyView_emptyDescription)
            findViewById<ImageView>(R.id.emptyViewImage).setImageDrawable(getDrawable(R.styleable.EmptyView_emptyImage))
        }
    }

}