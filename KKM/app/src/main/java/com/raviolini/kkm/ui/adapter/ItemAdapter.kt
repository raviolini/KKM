package com.raviolini.kkm.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raviolini.kkm.databinding.ListLayoutKosBinding
import com.raviolini.kkm.data.kos.Item


class ItemAdapter(private val dataItem : ArrayList<Item>, private val clickListener : (String, View)->Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

        inner class ItemViewHolder(private val binding : ListLayoutKosBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(item : Item,click: (String, View) -> Unit ){
                binding.data = item
                binding.root.transitionName = item.name
                binding.root.setOnClickListener{
                    click(item.name, binding.root)
                }
            }
        }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemData(items : List<Item>){
        dataItem.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListLayoutKosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        return holder.bind(dataItem[position], clickListener)
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }
}