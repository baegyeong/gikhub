package com.example.gikhub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.findFragment
import com.example.gikhub.navigation.TalkFragment
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk_write.*

class TalkWriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_talk_write, container, false)
        val click_cancel = view.findViewById<Button>(R.id.cancel_write)
        val fragmentManager = (activity as MainActivity)!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        click_cancel.setOnClickListener {
            val talkFragment = TalkFragment()
            framelayout_write.removeAllViews()
            fragmentTransaction.add(R.id.framelayout_write, talkFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        val click_complete = view.findViewById<Button>(R.id.complete_write)
        click_complete.setOnClickListener {
            val talkViewFragment = TalkViewFragment()
            framelayout_write.removeAllViews()
            fragmentTransaction.add(R.id.framelayout_write, talkViewFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
           return view
    }

}