package com.wilsonrc.favoritemovies.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


object NetworkTool {

    fun isOnline(): Single<Boolean> {
        return Single.fromCallable {
            try {
                val timeoutMs = 1500
                val sock = Socket()
                val sockaddr = InetSocketAddress("8.8.8.8", 53)

                sock.connect(sockaddr, timeoutMs)
                sock.close()

                true
            } catch (e: IOException) {
                false
            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}