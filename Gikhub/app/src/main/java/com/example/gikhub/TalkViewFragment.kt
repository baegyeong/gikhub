package com.example.gikhub

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gikhub.navigation.TalkFragment
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk_view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TalkViewFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    private lateinit var commentRecyclerView : RecyclerView
    private lateinit var replyRecyclerView: RecyclerView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_talk_view, container, false)

        commentRecyclerView = view.findViewById(R.id.comment_group)

        var title = arguments?.getString("title")
        var content = arguments?.getString("content")

        val postTitle = view.findViewById<TextView>(R.id.post_title)
        postTitle.setText("$title")
        val postContent = view.findViewById<TextView>(R.id.post_content)
        postContent.setText("$content")

        // 회원가입 때의 닉네임 말고 마이페이지의 닉네임을 가져와야 할듯
        val postWriter = view.findViewById<TextView>(R.id.post_writer)
        postWriter.setText("$nickname_input")

        val postTime = view.findViewById<TextView>(R.id.write_time)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")
        val formatted = current.format(formatter)
        postTime.setText("$formatted")

        val detail = view.findViewById<ImageView>(R.id.detail)
        detail.setOnClickListener{
            var popupMenu = PopupMenu(requireContext(), detail)
            popupMenu.menuInflater.inflate(R.menu.write_menu, popupMenu.menu)

            val talkWriteFragment = TalkWriteFragment()
            val fragmentManager = (activity as MainActivity)!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId){
                    R.id.action_refactor-> {
                        framelayout_view.removeAllViews()
                        fragmentTransaction.add(R.id.framelayout_view, talkWriteFragment)
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                        true
                    }
                    R.id.action_delete->{
                        true
                    }
                    else -> false
                }

            }
            popupMenu.show()
        }

        return view

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sendComment = view.findViewById<ImageView>(R.id.send_comment)
        val commentName = view.findViewById<TextView>(R.id.name)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")
        val formatted = current.format(formatter).toString()
        val itemList = ArrayList<CommentData>()
        val replyList = ArrayList<CommentData>()
        val commentCount = view.findViewById<TextView>(R.id.comment_count)

        commentCount.setText("댓글 ${itemList.size}")

        sendComment.setOnClickListener {
            val inputComment = view.findViewById<EditText>(R.id.input_comment).text.toString()
            if(inputComment!="") {
                itemList.add(CommentData("$commentName", "$inputComment", "$formatted"))
            }
            commentRecyclerView.adapter = CommentAdapter(itemList)
            commentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//            replyRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            commentCount.setText("댓글 ${itemList.size}")
            input_comment.setText(null)
        }


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
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}