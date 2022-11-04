package com.gfq.summery.net

import retrofit2.http.*

/**
 *  2022/4/12 17:44
 * @auth gaofuq
 * @description
 */
interface ServiceApi {


    @POST("chat/create")
    @FormUrlEncoded
    suspend fun createChat(@Field("userId") userId: Int?): Response<Any?>
}