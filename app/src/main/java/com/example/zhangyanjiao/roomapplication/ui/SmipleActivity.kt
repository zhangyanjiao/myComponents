package com.example.zhangyanjiao.roomapplication.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.zhangyanjiao.roomapplication.R
import com.example.zhangyanjiao.roomapplication.simpleDemo.MyViewModel
import com.example.zhangyanjiao.roomapplication.simpleDemo.UserDao
import kotlinx.android.synthetic.main.activity_main.*

class SmipleActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var myViewModel: MyViewModel
    private lateinit var dao: UserDao
    private var age = 0
    private val name = "name"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_create.setOnClickListener(this)
        bt_insert.setOnClickListener(this)
        bt_query.setOnClickListener(this)
        bt_next.setOnClickListener(this)
        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.bt_create -> {
                createDB()
            }
            R.id.bt_insert -> {
                insertDB()
            }
            R.id.bt_query -> {
                queryDB()
            }
        }
    }

    private fun queryDB() {
        myViewModel.getuserLiveData(this).observe(this, Observer {
            it?.forEach {
                Log.d("SmipleActivity", "${it.id} ${it.name}\n")
            }
        })

    }

    private fun insertDB() {
        myViewModel.insertDB(name + age, age++)
    }

    private fun createDB() {
        myViewModel.createDB(this)
    }
}
