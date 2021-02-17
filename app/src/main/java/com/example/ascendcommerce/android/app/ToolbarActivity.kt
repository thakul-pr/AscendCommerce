package com.example.ascendcommerce.android.app

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ascendcommerce.R

abstract class ToolbarActivity : AppCompatActivity() {
    private var customTitleTextView: TextView? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        setupToolbar()
    }

    override fun setTitle(@StringRes titleId: Int) {
        customTitleTextView?.text = getString(titleId)
    }

    private fun setupToolbar() {
        findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            val a = theme.obtainStyledAttributes(intArrayOf(R.attr.displayOptions))

            if (a.hasValue(0)) {
                supportActionBar?.displayOptions = a.getInt(0, 0)
            }

            a.recycle()

            customTitleTextView = findViewById(R.id.title)
        }
    }
}