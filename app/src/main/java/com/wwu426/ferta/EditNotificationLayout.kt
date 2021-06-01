package com.wwu426.ferta

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * very helpful documentation about this view
 */
class EditNotificationLayout : LinearLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    private fun initView() {
        inflate(context, R.layout.edit_notification_layout, this)
    }

}