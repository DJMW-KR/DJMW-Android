package com.pss.djmw_android.widget.extension

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//Activity Intent
fun AppCompatActivity.startActivityWithFinish(context: Context, activity: Class<*>) {
    startActivity(Intent(context, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    this.finish()
}