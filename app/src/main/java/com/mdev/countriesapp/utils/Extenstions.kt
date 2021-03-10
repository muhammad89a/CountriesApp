package com.mdev.countriesapp.utils

import android.widget.ImageView
import com.mdev.countriesapp.R
import android.net.Uri
import android.widget.TextView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


/**
 *  Helper function that either loads image or placeholder
 */
fun ImageView.loadImageOrDefault(imgUrl: String) {
    if (imgUrl.isNotEmpty()) {
        try {
            GlideToVectorYou
                .init()
                .with(context)
                .setPlaceHolder(android.R.drawable.stat_sys_download_done, android.R.drawable.stat_notify_error)
                .load(Uri.parse(imgUrl), this);
        }catch (e:Exception){
            
        }
    }
    else
        this.setImageResource(android.R.drawable.stat_notify_error)
}

fun TextView.showAreaOrNotFound(area: Double?) {
    this.text = area?.toString() ?: context.getString(R.string.not_found)
}