package com.example.gikhub.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gikhub.MainActivity
import com.example.gikhub.R
import com.example.gikhub.TalkWriteFragment
import com.example.gikhub.databinding.ActivityMainBinding
import com.example.gikhub.databinding.FragmentTalkBinding
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk.view.*
import kotlinx.android.synthetic.main.item_context.view.*
//
class TalkFragment :Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater!!.inflate( R.layout.fragment_talk, container,false)
        val click_btn:Button = view.findViewById(R.id.write_btn)
        click_btn.setOnClickListener(this)
        return view
    }
    companion object{
        fun newInstance(): TalkFragment{
            return TalkFragment()
        }
    }
    override fun onClick(v: View?){
        when(v?.id){
            R.id.write_btn -> {
                Log.d("please","제발..")
            }
            else->{

            }
        }
    }

}
//    override fun onViewCreated(view:View, savedInstanceState: Bundle?){
//        super.onViewCreated(view, savedInstanceState)
//        setOnClickListener()
//    }
//    private fun setOnClickListener(){
//        val btnSequence = view.container.children
//        btnSequence.forEach( btn->
//            btn.setOnClickListener(this)
//        )
//    }

//
//    inner class ContextRecyclerviewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
//        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
//            var view = LayoutInflater.from(p0.context).inflate(R.layout.item_context, p0, false)
//            return CustomViewHolder(view)
//        }
//
//        private inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view)
//        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
//            var view = p0.itemView
//            view.title.text = "title"
//            view.content.text = "댓글 3"
//            view.comment.text = "comment"
//        }
//
//        override fun getItemCount(): Int {
//            return 0
//        }
//    }