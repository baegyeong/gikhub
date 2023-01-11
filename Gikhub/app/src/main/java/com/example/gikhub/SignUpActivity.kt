package com.example.gikhub

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.red
import android.graphics.Rect
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern
import kotlin.reflect.typeOf

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

        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        lateinit var email:TextView
        email = findViewById(R.id.email_register)

        fun checkEmail():Boolean{
            var email = email_register.text.toString().trim()
            val p = Pattern.matches(emailValidation, email)
            if (p) {
                //이메일 형태가 정상일 경우
                email_register.setTextColor(R.color.black.toInt())
                return true
            } else {
                email_register.setTextColor(-65536)
                return false
            }
        }

        email_register.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmail()
            }
        })


        signup_btn.setOnClickListener {
            var email = email_register.text.toString()
            var passwd = passwd_register.text.toString()
            var name = name_input.text.toString()
            var nickname = nickname_input.text.toString()
            var phone = phone_number.text.toString()
            Log.d("info", "$email, $passwd, $name, $nickname, $phone")

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
                    if(!checkEmail())
                        Toast.makeText(this, "이메일 형식이 아닙니다.", Toast.LENGTH_LONG).show()
                    else {
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        startActivity(goLogin)
                        //정보들 저장하는 코드
                    }


                }

            }



            nickname_input.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if(nickname_input.length()<2){
                        Log.d("length", "${nickname_input.length()}")
                    nickname_input.setTextColor(Color.parseColor("#ff0000"))
                }

                }
            })
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