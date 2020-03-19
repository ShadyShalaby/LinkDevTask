package com.shady.linkdevtask.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.shady.linkdevtask.ui.adapters.ArticleViewHolder
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun getDateFormattedFromString(str: String, formatNeeded: String): String {
            try {
                val index = str.indexOf("T")
                return SimpleDateFormat(formatNeeded, Locale.US)
                    .format(Date.valueOf(str.substring(0, index)))
            } catch (e: Exception) {
                Log.e(ArticleViewHolder::class.simpleName, e.message, e)
            }

            return str
        }

        fun openUrlInBrowser(context: Context?, url: String) {
            if (url.isEmpty())
                return
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            context?.startActivity(openURL)
        }
    }
}