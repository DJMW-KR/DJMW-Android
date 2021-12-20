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
import com.pss.djmw_android.widget.utils.Utils
import com.pss.djmw_android.widget.utils.Utils.checkSdkVersion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()

    // TODO: 2021-11-15 앱을 업데이트 할때 마다 버전 업 해주기기 (0.0.0 은 점검중이라는 의미입니다)
    private val appVersion = "1.0.0"

    override fun init() {
        Utils.checkSdkVersion(this)
        checkVersion()
            .addOnSuccessListener {
                if (it.value == appVersion) {
                    //핸드폰으로 테스트 할때
                    //checkKakaoLogin()

                    //컴퓨터 에뮬레이터로 테스트 할때
                    checkPcLogin()
                } else if (it.value == "0.0.0") longShowToast("앱이 점검중 입니다, 잠시만 기다려 주세요!")
                else longShowToast("앱이 최신버전이 아닙니다, 앱을 업데이트 하세요!")
            }
            .addOnFailureListener {
                Log.d("로그","인터넷 연결 에러 : $it")
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

    private fun getUserInfo(userUid: String) = mainViewModel.getUserInfo(userUid)

    private fun getUserRankingInfo() = mainViewModel.getUserRankingInfo()

    private fun observeViewModel() {
        //Splash 값 가져오는 로직 순서 = getUserInfo -> getQuestion -> getUserParticipationInfo -> getUserRankingInfo

        mainViewModel.eventGetManQuestion.observe(this, {
            mainViewModel.getWomanQuestion()
        })

        mainViewModel.eventGetWomanQuestion.observe(this, {
            splashViewModel.getUserParticipationInfo(mainViewModel.eventGetUserInfo.value!!.userId)
        })

        mainViewModel.eventUserRankingInfo.observe(this,{ ranking ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putParcelableArrayListExtra("manQuestionList", mainViewModel.manQuestionList)
            intent.putParcelableArrayListExtra("womanQuestionList", mainViewModel.womanQuestionList)
            intent.putParcelableArrayListExtra("userRankingList", mainViewModel.userRankingList)
            intent.putExtra("userParticipation",splashViewModel.eventUserParticipationInfo.value)
            Log.d("로그","splash activity 질문 통계 : ${splashViewModel.eventUserParticipationInfo.value}")
            intent.putExtra("userRanking", ranking)
            with(mainViewModel.eventGetUserInfo.value!!){
                intent.putExtra(
                    "userInfo",
                    UserInfo(
                        userName = this.userName,
                        userId = this.userId,
                        participationQuestion = this.participationQuestion,
                        answerQuestion = this.answerQuestion,
                        score = this.score,
                        sex = this.sex,
                        stop = this.stop
                    )
                )
            }

            startActivity(intent)
            finish()
        })

        mainViewModel.eventGetUserInfo.observe(this, {
            //fireStore에서 질문 받아오기
            mainViewModel.getManQuestion()
        })

        splashViewModel.eventUserParticipationInfo.observe(this,{
            getUserRankingInfo()
        })

        splashViewModel.eventError.observe(this,{
            when(it){
                0 -> shortShowToast("사용자 참여통계 정보를 가져오는데 오류가 발생했습니다")
            }
        })

        mainViewModel.eventError.observe(this, {
            when (it) {
                0 -> shortShowToast("사용자 정보를 가져오는데 오류가 발생했습니다")
                1 -> shortShowToast("퀴즈를 불러오는데 오류가 발생해습니다")
            }
        })
    }
}