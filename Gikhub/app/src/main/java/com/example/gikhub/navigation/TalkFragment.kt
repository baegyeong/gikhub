package com.example.gikhub.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.gikhub.R
import com.example.gikhub.TalkWriteFragment
import com.example.gikhub.MainActivity
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.fragment_talk.*
import retrofit2.http.POST


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
        val spinner = view.findViewById<Spinner>(R.id.spinner)

        // 어댑터 설정
        spinner.adapter = ArrayAdapter.createFromResource(activity as MainActivity, R.array.itemList, R.layout.spinner_item)
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0->{
                        Log.d("spinner","최신순")
                    }
                    1->{
                        Log.d("spinner","댓글순")
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        return view
    }
}
