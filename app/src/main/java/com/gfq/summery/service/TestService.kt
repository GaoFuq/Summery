package com.gfq.summery.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 *  2022/5/20 10:25
 * @auth gaofuq
 * @description
 */
class TestService:Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}