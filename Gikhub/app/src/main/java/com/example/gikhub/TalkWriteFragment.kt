package com.example.gikhub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import com.example.gikhub.databinding.FragmentTalkBinding
import com.example.gikhub.databinding.FragmentTalkViewBinding
import com.example.gikhub.databinding.FragmentTalkWriteBinding
import com.example.gikhub.navigation.TalkFragment
import kotlinx.android.synthetic.main.fragment_talk_write.*

class TalkWriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_talk_write, container, false)

        val click_complete = view.findViewById<Button>(R.id.complete_write)
        click_complete.setOnClickListener {
            (activity as MainActivity).changeFragment(2)
        }

        val click_cancel = view.findViewById<Button>(R.id.cancel_write)
        click_cancel.setOnClickListener {
            (activity as MainActivity).changeFragment(3)
        }
        return view
    }
}