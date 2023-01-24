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
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.findFragment
import com.example.gikhub.navigation.TalkFragment
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk_write.*
import kotlinx.android.synthetic.main.item_context.*
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
        val content: String
    )

    interface postInterface {
        @POST("/api/talktalk/add")
        fun addPost(@Body post: Post): Call<Post>
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val addPostRetrofit = retrofit.create(postInterface::class.java)


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
            val writeTitle = view.findViewById<EditText>(R.id.write_title)
            val writeContent = view.findViewById<EditText>(R.id.write_content)
            val title = writeTitle.text.toString()
            val content = writeContent.text.toString()
            val info = Post("$title", "$content")
            Log.d("info","$info")
         //    서버 통신
            addPostRetrofit.addPost(info).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful())
                        Log.d("post", "success ${response}")
                    else
                        Log.d("post", "but ${response.errorBody()?.string()!!}")
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.d("post", "error:${t.message}")
                }

            })


            if(title.isEmpty())
                Toast.makeText(view.context,"제목을 입력하세요.", Toast.LENGTH_SHORT).show()
            else if(content.isEmpty())
                Toast.makeText(view.context,"내용을 입력하세요.", Toast.LENGTH_SHORT).show()
            else if(!(title.isEmpty())&&title.length>26)
                Toast.makeText(view.context,"제목은 25자까지 입력할 수 있습니다.", Toast.LENGTH_SHORT).show()
            else{
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
