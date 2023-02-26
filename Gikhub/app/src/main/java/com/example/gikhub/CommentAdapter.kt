package com.example.gikhub

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(private val data: ArrayList<CommentData>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        val commentResource = view.findViewById<LinearLayout>(R.id.comment_resource)
        val reply = view.findViewById<ImageButton>(R.id.reply)
        reply.setOnClickListener{
            commentResource.setBackgroundColor(Color.parseColor("#FFD2EBFF"))
        }
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.view.name.text = data[position].name
        holder.view.comment.text = data[position].comment
        holder.view.date.text = data[position].date
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
