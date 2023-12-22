package com.ahsan.gitrepos.data.remote.network

import com.ahsan.gitrepos.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class MyPackNameInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("package", Constants.APP_PACK_NAME)
        return chain.proceed(request.build())
    }
}