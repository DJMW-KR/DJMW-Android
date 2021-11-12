package com.pss.djmw_android.view.signin

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.databinding.ActivitySignInBinding
import com.kakao.sdk.user.UserApiClient
import com.pss.djmw_android.viewmodel.SignInViewModel
import com.kakao.sdk.user.model.User


class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel by viewModels<SignInViewModel>()


    override fun init() {
        binding.activity = this
        initColor()
        checkSdkVersion()
        observeViewModel()
    }

    fun clickKakaoLoginBtn(view: View) = viewModel.clickKakaoLoginBtn()

    private fun observeViewModel() {
        viewModel.eventKakaoLogin.observe(this, {
            UserApiClient.instance
                .loginWithKakaoTalk(this) { oAuthToken: OAuthToken?, error: Throwable? ->
                    if (error != null) {
                        when (error.message) {
                            "KakaoTalk not installed" -> shortShowToast("카카오톡이 다운로드 되어있지 않습니다")
                        }
                        Log.e("TAG", "로그인 실패 ${error.message}")
                    } else if (oAuthToken != null) {
                        Log.i("TAG", "로그인 성공(토큰) : " + oAuthToken.accessToken)
                        shortShowToast("로그인에 성공했습니다")
                    }
                }
        })
    }

    private fun initColor() {
        binding.kakaoLogo.setColorFilter(Color.parseColor("#1E1C01"))
    }

    private fun checkSdkVersion() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}