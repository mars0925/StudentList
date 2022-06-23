package com.solidyear.studentlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.solidyear.studentlist.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import java.util.ArrayList

class MainActivity: AppCompatActivity(), View.OnClickListener {
    val studentList = mutableListOf<Student>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.insertData.setOnClickListener(this)
        binding.update.setOnClickListener(this)
        binding.remove.setOnClickListener(this)
        binding.updateAll.setOnClickListener(this)
        setContentView(binding.root)

        initData()
        binding.studentList.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter()
        binding.studentList.adapter = adapter
        adapter.submitList(studentList)

    }

    private fun initData() {
        for (i in 1..30) {
            studentList.add(Student("$i","student $i",(Math.random()*100).toInt()))
        }
    }

    override fun onClick(view: View?) {
       when (view?.id){
           R.id.remove->removeData()
           R.id.insertData->insertData()
           R.id.update->updateData()
           R.id.updateAll->updateAll()
       }
    }

    private fun updateAll() {
        val newList = mutableListOf<Student>()
        (1..5).forEach{i->
            newList.add(Student("$i","student $i",(Math.random()*100).toInt()))
        }
        adapter.submitList(newList)

    }

    /**新增五筆成績*/
    private fun insertData() {
        val count = adapter.currentList.size
        val student = adapter.currentList[count - 1]

        val newList = mutableListOf<Student>()
        newList.addAll(adapter.currentList)

        (1..5).forEach{
            val newID = student.id .toInt() + it
            val name = "student $newID"
            val eng = (Math.random()*100).toInt()
            newList.add(Student(newID.toString(),name,eng))
        }

        adapter.submitList(newList)
    }

    /**更新第一筆成績*/
    private fun updateData() {

        val updateList = mutableListOf<Student>()
        updateList.addAll(adapter.currentList)

        updateList[0].apply {
            eng = 99
        }

        adapter.submitList(updateList)//更新recyclerview
        adapter.notifyItemChanged(0)
    }

    /**刪除第一筆成績*/
    private fun removeData() {
        val updateList = mutableListOf<Student>()
        updateList.addAll(adapter.currentList)
        updateList.removeAt(0)
        adapter.submitList(updateList)
    }
}