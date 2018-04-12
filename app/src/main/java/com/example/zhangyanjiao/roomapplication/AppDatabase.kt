package com.example.zhangyanjiao.roomapplication

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.example.zhangyanjiao.roomapplication.simpleDemo.UserDao

/**
 * @author zhangyanjiao
 * @desc 通过该类 我们可以创建数据库 但是 我们都知道操作数据库时我们需要保证唯一性 否则会出现并发修改异常  那么我们这里使用单例试下一下
 */
@Database(entities = [(UserBean::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context): AppDatabase {
            //创建数据库操作实体类 此时我们创建了一个user数据库 同时获取了数据库操作对象
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "user")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                TODO 创建数据库的时候 添加一些操作  比如 预添加一些数据等
                                super.onCreate(db)
                                Log.d("AppDatabase", "onCreate")
                            }
                        }).build()

            }
            return instance as AppDatabase
        }
    }
}