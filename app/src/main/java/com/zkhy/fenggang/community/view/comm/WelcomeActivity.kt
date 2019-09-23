package com.zkhy.fenggang.community.view.comm

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.sinothk.comm.utils.IntentUtil
import com.zkhy.fenggang.community.MainActivity
import com.zkhy.fenggang.community.R
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        when (Random().nextInt(3)) {
            0 -> launchIv.setImageResource(R.drawable.launch)
            1 -> launchIv.setImageResource(R.drawable.launch1)
            else -> launchIv.setImageResource(R.drawable.launch2)
        }

        Handler().postDelayed({

            //            val user: WmUser? = DataCache.getUserInfo()
//
//            if (user != null && DataCache.isAutoLogin()) { // 免登陆
//
//            } else {
//                IntentUtil.openActivity(this@WelcomeActivity, LoginActivity::class.java).finish(true)
//                    .start()
//            }

            IntentUtil.openActivity(this@WelcomeActivity, MainActivity::class.java).finish(true)
                .putIntExtra(MainActivity.FROM_TYPE, MainActivity.FROM_WELCOME)
                .start()

        }, 2000)
    }
}
