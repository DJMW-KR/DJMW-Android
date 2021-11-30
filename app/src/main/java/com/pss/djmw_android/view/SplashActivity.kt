package com.pss.djmw_android.view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.data.model.UserInfo
import com.pss.djmw_android.databinding.ActivitySplashBinding
import com.pss.djmw_android.view.main.MainActivity
import com.pss.djmw_android.view.signin.SignInActivity
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.viewmodel.SplashViewModel
import com.pss.djmw_android.widget.extension.startActivityWithFinish
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()

    // TODO: 2021-11-15 앱을 업데이트 할때 마다 버전 업 해주기기
    private val appVersion = "1.0.0"

    override fun init() {
        checkSdkVersion()
        checkVersion()
            .addOnSuccessListener {
                if (it.value == appVersion) {
                    //핸드폰으로 테스트 할때
                    //checkKakaoLogin()

                    //컴퓨터 에뮬레이터로 테스트 할때
                    checkPcLogin()
                } else longShowToast("앱이 최신버전이 아닙니다, 앱을 업데이트 하세요!")
            }
            .addOnFailureListener {
                shortShowToast("인터넷 연결을 확인하세요")
            }
        observeViewModel()
    }

    private fun checkVersion() = splashViewModel.checkAppVersion()

    private fun checkKakaoLogin() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //로그인 필요
                        startSignIn()
                    } else {
                        //기타 에러
                        longShowToast("에러가 발생했습니다, 오류문자 : ${error.message}")
                    }
                } else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    Log.d("TAG", "checkKakaoLogin 토큰 유효성 체크 성공")
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.d("TAG", "datastore 값 :  ${splashViewModel.readDataStore()}")

                        if (splashViewModel.readDataStore() == "YES") {

                            // TODO: 2021-11-29 kakao id저장 후 그 아이디 가져오는 코드 쓰기
                            //mainViewModel.getUserInfo()
                        } else startSignIn()
                    }
                }
            }
        } else {
            //로그인 필요
            startSignIn()
        }
    }

    private fun checkPcLogin() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("TAG", "datastore 값 :  ${splashViewModel.readDataStore()}")

            //fireStore에서 사용자 정보 받아오기
            getUserInfo("1000000000")
        }
    }

    private fun startSignIn() = this.startActivityWithFinish(this, SignInActivity::class.java)

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

    private fun getUserInfo(userUid: String) = mainViewModel.getUserInfo(userUid)

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

    private fun observeViewModel() {
        mainViewModel.eventGetQuestion.observe(this, {
            val array = mainViewModel.questionList
            val intent = Intent(this, MainActivity::class.java)
            intent.putParcelableArrayListExtra("questionList", array)
            intent.putExtra(
                "userInfo",
                UserInfo(
                    userName = mainViewModel.eventGetUserInfo.value!!.userName,
                    userId = mainViewModel.eventGetUserInfo.value!!.userId,
                    participationQuestion = mainViewModel.eventGetUserInfo.value!!.participationQuestion,
                    answerQuestion = mainViewModel.eventGetUserInfo.value!!.answerQuestion
                )
            )
            startActivity(intent)
            finish()
        })

        mainViewModel.eventGetUserInfo.observe(this, {
            //fireStore에서 질문 받아오기
            mainViewModel.getQuestion()
        })

        mainViewModel.eventError.observe(this, {
            when (it) {
                0 -> shortShowToast("사용자 정보를 가져오는데 오류가 발생했습니다")
                1 -> shortShowToast("퀴즈를 불러오는데 오류가 발생해습니다")
            }
        })
    }
}