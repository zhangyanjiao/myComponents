package com.example.paginglibraryapplication.forwardDemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import com.example.paginglibraryapplication.R
import com.example.zhangyanjiao.roomapplication.UserBean
import com.example.zhangyanjiao.roomapplication.forwardDemo.BaseAdapter
import com.example.zhangyanjiao.roomapplication.forwardDemo.BaseHolder
import com.example.zhangyanjiao.roomapplication.forwardDemo.ForwardViewModel
import kotlinx.android.synthetic.main.activity_for_ward.*

/**
 * simple中我们 使用了简单的Room 现在结合 Paging Library我们用下
 */
class ForWardActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ForWardActivity"
    private val adapter by lazy {
        BaseAdapter()
    }
    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(ForwardViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_ward)
        bt_insert.setOnClickListener(this)
        bt_query.setOnClickListener(this)
        bt_delete.setOnClickListener(this)
        initRecycleView()
    }

    private fun initRecycleView() {
        recycle_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycle_view.adapter = adapter
//        添加item管理
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
               return  makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                (viewHolder as? BaseHolder)?.itemBean?.let {
                    viewModel.delete(it)
                }
            }

        }).attachToRecyclerView(recycle_view)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.bt_insert -> {
                insert()
            }
            R.id.bt_query -> {
                query()
            }
            R.id.bt_delete -> {
                delete()
            }
        }
    }

    private fun delete() {
        Log.d(TAG, "delete")
        viewModel.delete(UserBean(0, name + count, --count))
    }

    private fun query() {
        Log.d(TAG, "query")
        viewModel.allUsers.observe(this, Observer(adapter::submitList))
    }

    val name = "name"
    private var count = 0
    private fun insert() {
        viewModel.insert(UserBean(0, name + count, count++))
        Log.d(TAG, "insert")
    }
}
