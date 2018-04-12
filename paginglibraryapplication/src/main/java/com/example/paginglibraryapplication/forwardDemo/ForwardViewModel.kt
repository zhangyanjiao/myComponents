package com.example.zhangyanjiao.roomapplication.forwardDemo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.zhangyanjiao.roomapplication.AppDatabase
import com.example.zhangyanjiao.roomapplication.UserBean
import com.example.zhangyanjiao.roomapplication.ioThread

/**
 * @author zhangyanjiao
 * @desc 使用viewModel实现数据的保持
 */

class ForwardViewModel(app: Application) : AndroidViewModel(app) {
    //    获取数据库操作对象
    val dao = AppDatabase.get(app).userDaoPL()

    companion object {
        private const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = true
    }

    val allUsers = LivePagedListBuilder(dao.queryAll(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
            .build()).build()

    fun insert(user: UserBean) {
        ioThread {
            dao.insert(user)
        }
    }

    fun delete(user: UserBean) {
        ioThread {
            dao.delete(user)
        }
    }

}
