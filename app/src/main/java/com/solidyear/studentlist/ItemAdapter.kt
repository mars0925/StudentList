package com.solidyear.studentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solidyear.studentlist.databinding.ItemBinding

/**
 * Time: 2021/11/1
 * Author: mars
 * Description:
 */

class ItemAdapter:ListAdapter<Student,RecyclerView.ViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is ItemViewHolder){
           holder.bind(currentList[position])
       }

    }

    companion object{
        val diffCallBack = object: DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ItemViewHolder(private var binding: ItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Student){
            item.apply {
                binding.id.text = "ID:$id"
                binding.name.text = "姓名：$name"
                binding.eng.text = "英文成績：$eng"
            }
        }

        companion object{
            fun from(parent:ViewGroup):ItemViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(inflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }
}