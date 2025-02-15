package com.example.gikhub

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
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
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

//    data class User(
//        val email:String,
//        val password:String
//    )
//
//    interface LoginInterface{
//        @POST("/api/login")
//        fun getUser(@Body info: User): Call<User>
//    }
//    var gson = GsonBuilder().setLenient().create()
//
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://10.0.2.2:8080/")
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()
//    val loginUser = retrofit.create(LoginInterface::class.java)

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
            var savedEmail: Boolean = true
            var savedPW: Boolean = true
//            val user = User("$email", "$passwd")
//            loginUser.getUser(user).enqueue(object:Callback<User>{
//                override fun onResponse(call: Call<User>, response: Response<User>) {
//                    if(response.isSuccessful()){
//                        Log.d("login","success ${response}")
//                        Log.d("login", "$user")
//                        savedEmail = true
//                        savedPW = true
//                    }else{
//                        Log.d("login","but ${response.errorBody()}")
//                        savedEmail = false
//                        savedPW = false
//                    }
//                }
//
//                override fun onFailure(call: Call<User>, t: Throwable) {
//                    Log.d("login","error:${t.message}")
//                    savedEmail = false
//                    savedPW = false
//                }
//
//            })
            // 입력한 값과 비교
            if (savedEmail && savedPW) {
                Toast.makeText(this, "환영합니다!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else if (!savedEmail && savedPW) {
                Toast.makeText(this, "이메일을 다시 확인해주세요.", Toast.LENGTH_LONG).show()
            } else if (savedEmail && !savedPW) {
                Toast.makeText(this, "비밀번호를 다시 확인해주세요.", Toast.LENGTH_LONG).show()
            } else {
                dialog()
            }
        }

        var testEmail = findViewById<TextInputLayout>(R.id.email_btn)
        // 이메일 유효성 검사
        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        fun checkEmail():Boolean{
            var inputEmail = email.text.toString().trim()
            val p = Pattern.matches(emailValidation, inputEmail)
            if (p) {
                //이메일 형태가 정상일 경우
                email.setTextColor(R.color.black.toInt())
                testEmail.error = null
                return true
            } else {
                email.setTextColor(-65536)
                testEmail.error = "이메일 형식이 아닙니다."
                return false
            }
        }

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmail()
            }
        })

        var testPW = findViewById<TextInputLayout>(R.id.passwd_btn)
        // 최소 비밀번호 글자수 설정
        fun checkPW():Boolean{
            if(passwd.length()<8){
                testPW.error = "비밀번호는 8자~16자 입니다."
                return false
            }else{
                testPW.error = null
                return true
            }
        }

        passwd.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkPW()
            }

        })


        login_btn.setOnClickListener {
            if(checkEmail()) {
                compareInfo()
                checkPW()
            }
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