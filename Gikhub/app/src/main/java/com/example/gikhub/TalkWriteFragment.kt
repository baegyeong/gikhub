package com.example.gikhub

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.findFragment
import com.example.gikhub.navigation.TalkFragment
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk_write.*
import org.checkerframework.checker.units.qual.m
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class TalkWriteFragment : Fragment() {

    data class Post(
        @SerializedName("title")
        val title: String,
        @SerializedName("content")
        val content: String,
        @SerializedName("user")
        val user: String
    )

    interface postInterface {
        @POST("/api/talktalk/add")
        fun addPost(@Body post: Post): Call<List<Post>>
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val addPost = retrofit.create(TalkWriteFragment.postInterface::class.java)


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
        val info = Post("$write_title", "$write_content", "unknown")
        click_complete.setOnClickListener {
         //    서버 통신
            addPost.addPost(info).enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful())
                        Log.d("post", "success ${response}")
                    else
                        Log.d("post", "but ${response.errorBody()?.string()!!}")
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Log.d("post", "error:${t.message}")
                }

            })

            val title = write_title.text.toString()
            val content = write_content.text.toString()
            if (!(title.isEmpty()) && !(content.isEmpty())) {
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putString("content", content)

                val talkViewFragment = TalkViewFragment()
                framelayout_write.removeAllViews()

                talkViewFragment.arguments = bundle

                fragmentTransaction.add(R.id.framelayout_write, talkViewFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

        }
        return view
    }

}
