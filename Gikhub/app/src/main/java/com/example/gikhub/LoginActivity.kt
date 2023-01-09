package com.example.gikhub

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        login_btn.setOnClickListener{
            signin()
        }
        val goSignUp = Intent(this, SignUpActivity::class.java)
        signup_msg2.setOnClickListener{startActivity(goSignUp)}
    }

    // 자동로그인
//    override fun onStart(){
//        super.onStart()
//        moveMainPage(auth?.currentUser)
//    }

    fun signin(){
        auth?.signInWithEmailAndPassword(email.text.toString(),passwd.text.toString())
            ?.addOnCompleteListener() {
                    task->
                if(task.isSuccessful){
                    // Login
                    moveMainPage(task.result.user)
                }else {
                    // Show the error message
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                }
            }
    }
    fun moveMainPage(user: FirebaseUser?){
        if(user!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
    // 화면 클릭시 키보드 내리기 및 포커스 제거
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}