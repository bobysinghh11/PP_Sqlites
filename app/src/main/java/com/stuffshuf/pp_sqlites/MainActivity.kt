package com.stuffshuf.pp_sqlites

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var tasks = arrayListOf<TableTask.Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = MyDbHelper(this)
        val taskDb = dbHelper.writableDatabase

        tasks = TableTask.getAllTasks(taskDb)

        val taskAdapter = TaskAdapter(tasks)
        lvTodolist.adapter = taskAdapter

        btnAdd.setOnClickListener {


                TableTask.insertTask(
                    taskDb, TableTask.Task(
                        null,
                        etNewItem.text.toString(),
                        false
                    )
                )
                tasks = TableTask.getAllTasks(taskDb)
                taskAdapter.updataTask(tasks)

        }
    }
}



