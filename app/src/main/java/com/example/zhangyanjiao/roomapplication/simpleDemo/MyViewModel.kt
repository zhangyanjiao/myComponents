package com.example.zhangyanjiao.roomapplication.simpleDemo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import com.example.zhangyanjiao.roomapplication.UserBean

/**
 * @author zhangyanjiao
 * @desc 我们尝试使用下ViewModel管理数据
 */
class MyViewModel(app: Application) : AndroidViewModel(app) {
    private var userLiveData: UserDataManager? = null

    fun createDB(context: Context) {
        if (userLiveData == null) {
            userLiveData = UserDataManager(context)
        }
    }

    fun getuserLiveData(context: Context): UserDataManager {
        if (userLiveData == null) {
            userLiveData = UserDataManager(context)
        }
        return userLiveData as UserDataManager
    }

    fun insertDB(name: String, age: Int) {
        userLiveData?.insertUser(UserBean(0, name, age))
    }
}
