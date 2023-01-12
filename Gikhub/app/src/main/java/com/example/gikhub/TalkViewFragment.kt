package com.example.gikhub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gikhub.databinding.ActivityMainBinding
import com.example.gikhub.databinding.FragmentTalkViewBinding


class TalkViewFragment : Fragment() {
    private var view: FragmentTalkViewBinding?=null
    private val binding get() = view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_talk_view, container, false)
        return view
    }
    override fun onDestroyView(){
        super.onDestroyView()
        view = null
    }

}