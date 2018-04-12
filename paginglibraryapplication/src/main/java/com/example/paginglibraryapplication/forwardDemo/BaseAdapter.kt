package com.example.zhangyanjiao.roomapplication.forwardDemo

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.example.zhangyanjiao.roomapplication.UserBean

/**
 * @author zhangyanjiao
 * @desc
 */
class BaseAdapter : PagedListAdapter<UserBean, BaseHolder>(disCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder = BaseHolder(parent)

    override fun onBindViewHolder(holder: BaseHolder, position: Int) = holder.bindTo(getItem(position))


    companion object {
        private val disCallback = object : DiffUtil.ItemCallback<UserBean>() {
            override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean =
                    oldItem == newItem
        }
    }

}

