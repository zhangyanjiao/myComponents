package com.example.zhangyanjiao.roomapplication.forwardDemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.paginglibraryapplication.R
import com.example.zhangyanjiao.roomapplication.UserBean

/**
 * @author zhangyanjiao
 * @desc
 */
class BaseHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)) {
    private val tvName = itemView?.findViewById<TextView>(R.id.tv_name)
     var itemBean: UserBean? = null
    fun bindTo(item: UserBean?) {
        itemBean = item
        tvName?.text = itemBean?.name
    }


}