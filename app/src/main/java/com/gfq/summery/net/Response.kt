package com.gfq.summery.net

import com.gfq.common.net.AbsResponse


class Response<T> : AbsResponse<T>() {
    val code: Int? = null
    val msg: String? = null
    val data: T? = null
    override fun isSuccess(): Boolean = code == 200

    override fun responseCode(): Int? = code

    override fun responseData(): T? = data

    override fun responseMessage(): String? = msg

}