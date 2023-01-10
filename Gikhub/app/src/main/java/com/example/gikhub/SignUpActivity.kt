package com.example.gikhub

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    var isExistBlank = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val goLogin = Intent(this, LoginActivity::class.java)

        // 이미 가입 된 이메일일 때
        fun dialog(){
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("가입된 정보")
            dialog.setMessage("이미 가입되어 있는 이메일입니다.\n로그인하시겠습니까?")
            var dialog_listener_yes = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        DialogInterface.BUTTON_POSITIVE ->
                            startActivity(goLogin)
                    }
                }
            }
            dialog.setPositiveButton("예", dialog_listener_yes)
            dialog.setNegativeButton("아니오", null)
            dialog.show()
        }

        fun minPW() {
            // 최소 비밀번호 글자수 설정(8자)
        }

        fun minNickname(testNickname: String){
                Log.d("nick", "${testNickname.length}")
        }

        signup_btn.setOnClickListener {
            var email = email_register.text.toString()
            var passwd = passwd_register.text.toString()
            var name = name.text.toString()
            var nickname = nickname.text.toString()
            var phone = phone_number.text.toString()
            Log.d("info", "$email, $passwd, $name, $nickname, $phone")
            // 이메일 형식 확인
//            val emailPattern = Patterns.EMAIL_ADDRESS
//            if(emailPattern.matcher(email).matches()){
//            } else{
//            }

        // 정보가 전부 입력됐는지 확인
            // 모든 항목이 입력되지 않았을 때
            if (email.isEmpty() || passwd.isEmpty() || name.isEmpty() || nickname.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "항목을 채워주세요", Toast.LENGTH_LONG).show()
                isExistBlank = true
            } else {
                isExistBlank = false
            }

            if(!isExistBlank){
                // 이메일 가져오는 코드
                val savedEmail: String = "0000@example.com"

                // 입력한 값과 비교
                if (email == savedEmail) {
                    dialog()
                }else{
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                    //정보들 저장하는 코드

                    //로그인 화면으로 이동
                    startActivity(goLogin)
                }
            }

            minNickname(nickname)
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