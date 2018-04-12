package com.example.zhangyanjiao.roomapplication.simpleDemo

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.zhangyanjiao.roomapplication.UserBean


/**
 * @author zhangyanjiao
 * @desc 数据库操作dao包
 * Room 会根据Dao 生成对应的实现类
 */
@Dao
interface UserDao {
    @Query("select * from user")
    fun getAll(): List<UserBean>

    @Insert
    fun insertAll(users: List<UserBean>)

    @Insert
    fun insertUser(user: UserBean)

    @Delete
    fun delete(user: UserBean)
}