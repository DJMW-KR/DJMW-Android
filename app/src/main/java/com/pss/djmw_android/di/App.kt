package com.pss.djmw_android.di

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.pss.djmw_android.widget.utils.KakaoKey.KAKAO_NATIVE_APP_KEY
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private var instance: App? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        KakaoSdk.init(this, KAKAO_NATIVE_APP_KEY)
    }
}