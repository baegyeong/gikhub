package com.example.gikhub

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val goSignUp = Intent(this, SignUpActivity::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signup_msg2.setOnClickListener { startActivity(goSignUp) }

        fun dialog(){
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그인 실패")
            dialog.setMessage("유효하지 않은 계정정보입니다.\n회원가입 하시겠습니까?")
            var dialog_listener_yes = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        DialogInterface.BUTTON_POSITIVE ->
                            startActivity(goSignUp)
                    }
                }
            }
            dialog.setPositiveButton("예", dialog_listener_yes)
            dialog.setNegativeButton("아니오", null)
            dialog.show()
        }

        fun compareInfo() {
            val email = email.text.toString()
            val passwd = passwd.text.toString()

            // 이메일, 비밀번호 정보 가져오는 코드
            val savedEmail: String = "0000@example.com"
            val savedPW: String = "12345678"

            // 입력한 값과 비교
            if (email == savedEmail && passwd == savedPW) {
                Toast.makeText(this, "환영합니다!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else if (email != savedEmail && passwd == savedPW) {
                Toast.makeText(this, "이메일을 다시 확인해주세요.", Toast.LENGTH_LONG).show()
            } else if (email == savedEmail && passwd != savedPW) {
                Toast.makeText(this, "비밀번호를 다시 확인해주세요.", Toast.LENGTH_LONG).show()
            } else {
                dialog()
            }
        }
        

        login_btn.setOnClickListener {
            compareInfo()
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