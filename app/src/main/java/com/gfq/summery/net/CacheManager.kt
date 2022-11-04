package com.gfq.summery.net

import com.gfq.common.system.sp.SPData
import com.gfq.common.system.sp.SPTableDelegate


/**
 *  2022/3/8 11:05
 * @auth gaofuq
 * @description
 */

object SpTable {
    object User {
        const val tableName = "userSelfInfo"
        const val keyName = "userSelfInfo_key"
    }
}




class TokenSp : SPTableDelegate<TokenCache>(TokenCache(""), defaultSPTableName)

data class TokenCache(
    var token: String?,
    override val dataKeyName: String = "token",
    override val spTableName: String = SPTableDelegate.defaultSPTableName,
) : SPData
