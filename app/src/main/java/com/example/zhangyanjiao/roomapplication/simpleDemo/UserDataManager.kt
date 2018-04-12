package com.example.zhangyanjiao.roomapplication.simpleDemo

import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.zhangyanjiao.roomapplication.AppDatabase
import com.example.zhangyanjiao.roomapplication.UserBean
import com.example.zhangyanjiao.roomapplication.ioThread

/**
 * @author zhangyanjiao
 * @desc User数据管理类 用于数据获取
 */
class UserDataManager : LiveData<List<UserBean>> {
    private var context: Context? = null
    private var userDao: UserDao? = null

    constructor(context: Context) {
        this.context = context
        userDao = AppDatabase.get(context.applicationContext).userDao()
    }

    fun insertUser(user: UserBean) {
        ioThread {
            userDao?.insertUser(user)
            val list = userDao?.getAll()
            postValue(list)
        }
    }

    override fun onActive() {
        super.onActive()
        ioThread {
                val list = userDao?.getAll()
                postValue(list)
        }

    }
}