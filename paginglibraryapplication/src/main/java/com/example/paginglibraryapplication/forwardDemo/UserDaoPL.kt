package com.example.zhangyanjiao.roomapplication.forwardDemo

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.zhangyanjiao.roomapplication.UserBean

/**
 * @author zhangyanjiao
 * @desc 使用paging Library 重新构建Dao
 */
@Dao
interface UserDaoPL{
    @Insert
    fun insert(user:UserBean)

    /**
     * 我们使用DataSource factory来构造数据源
     */
    @Query("select * from user")
    fun queryAll(): DataSource.Factory<Int,UserBean>

    @Delete
    fun delete(user:UserBean)
}
