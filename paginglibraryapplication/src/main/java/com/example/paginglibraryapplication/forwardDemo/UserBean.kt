package com.example.zhangyanjiao.roomapplication

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author zhangyanjiao
 * @desc user 实体类
 * 主键 id 自动生成
 * 字段name
 * 字段age
 */
@Entity(tableName = "user")
data class UserBean(@PrimaryKey(autoGenerate = true) var id: Int, var name: String, var age: Int)