package com.example.gikhub

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import com.example.gikhub.navigation.TalkFragment
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk_view.*

class TalkViewFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_talk_view, container, false)
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