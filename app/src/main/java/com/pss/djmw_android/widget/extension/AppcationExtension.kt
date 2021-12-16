package com.pss.djmw_android.widget.extension

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_android.widget.utils.LinearLayoutManagerWrapper

//true = visible , false = gone
fun View.setVisibility(v: Boolean) {
    this.visibility = if (v) View.VISIBLE else View.GONE
}

fun RecyclerView.showVertical(context: Context) {
    val linearLayoutManagerWrapepr =
        LinearLayoutManagerWrapper(context, LinearLayoutManager.VERTICAL, false)
    this.layoutManager = linearLayoutManagerWrapepr
}

//RecyclerView Horizontal
fun RecyclerView.showHorizontal(context: Context) {
    val linearLayoutManagerWrapepr =
        LinearLayoutManagerWrapper(context, LinearLayoutManager.VERTICAL, false)
    this.layoutManager = linearLayoutManagerWrapepr
}

//위 작업줄 아이콘 색상 설정
fun Activity.setStatusBarColor(color:Int){
    var flags = window?.decorView?.systemUiVisibility // get current flag
    if (flags != null) {
        if(isColorDark(color)){
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            window?.decorView?.systemUiVisibility = flags
        }else{
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window?.decorView?.systemUiVisibility = flags
        }
    }
    window?.statusBarColor = color
}

//status bar 색이 밝은지 검은지 확인
fun Activity.isColorDark(color:Int) : Boolean{
    val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
    return darkness >= 0.5
}