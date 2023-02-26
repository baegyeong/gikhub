package com.example.gikhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_comment.view.*

class ReplyAdapter(private val data: ArrayList<CommentData>) :RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>(){

    class ReplyViewHolder(val view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reply, parent,false)
        return ReplyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {
        holder.view.name.text = data[position].name
        holder.view.comment.text = data[position].comment
        holder.view.date.text = data[position].date
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
