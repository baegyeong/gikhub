package com.example.gikhub.navigation

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gikhub.R
import com.example.gikhub.TalkWriteFragment
import com.example.gikhub.MainActivity
import kotlinx.android.synthetic.main.fragment_talk.*


class TalkFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_talk, container,false)
        val btn = view.findViewById<Button>(R.id.write_btn)
        btn.setOnClickListener {
            val talkWriteFragment = TalkWriteFragment()
            val fragmentManager = (activity as MainActivity)!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            framelayout_main.removeAllViews()
            fragmentTransaction.add(R.id.framelayout_main, talkWriteFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        return view
    }

}
