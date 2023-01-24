package com.example.gikhub

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import com.example.gikhub.navigation.TalkFragment
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk_view.*
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TalkViewFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_talk_view, container, false)

        var title = arguments?.getString("title")
        var content = arguments?.getString("content")

        val postTitle = view.findViewById<TextView>(R.id.post_title)
        postTitle.setText("$title")
        val postContent = view.findViewById<TextView>(R.id.post_content)
        postContent.setText("$content")
        val postTime = view.findViewById<TextView>(R.id.write_time)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")
        val formatted = current.format(formatter)
        postTime.setText("$formatted")
        return view

    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed(){
                framelayout_view.removeAllViews()
                (activity as MainActivity)!!.supportFragmentManager.beginTransaction().add(R.id.framelayout_view, TalkFragment()).addToBackStack(null).commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach(){
        super.onDetach()
        callback.remove()
    }

}