package com.example.gikhub

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.icu.text.IDNA
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
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    data class User(
        val email:String,
        val password:String,
        val name:String,
        val nickName:String,
        val phoneNo:String
    )

    interface SignUpInterface{
        @GET("/validation/join")
        fun getUserPage(): Call<User>

        @POST("/validation/join")
        fun getUser(@Body info: User): Call<User>
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val registerUser = retrofit.create(SignUpInterface::class.java)


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

        // 이메일 유효성 검사
        var testEmail = findViewById<TextInputLayout>(R.id.email_btn)
        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        fun checkEmail():Boolean{
            var email = email_register.text.toString().trim()
            val p = Pattern.matches(emailValidation, email)
            if (p) {
                //이메일 형태가 정상일 경우
                email_register.setTextColor(R.color.black.toInt())
                testEmail.error = null
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

        // 비밀번호 검사
        var testPW = findViewById<TextInputLayout>(R.id.passwd_btn)
        val passwdValidation = "^(?=.*[A-Za-z])(?=.*[$@$!%*#?&.])(?=.*[0-9])[A-Za-z[0-9]\$@\$!%*#?&.]{8,16}$"

        fun checkPW():Boolean{
            var passwd = passwd_register.text.toString().trim()
            val p = Pattern.matches(passwdValidation, passwd)
            if (p) {
                passwd_register.setTextColor(R.color.black.toInt())
                testPW.error = null
                return true
            } else {
                passwd_register.setTextColor(-65536)
                testPW.error = "비밀번호는 숫자, 특수문자, 대/소문자를 포함한\n8자~16자이어야 합니다."
                return false
            }
        }

        passwd_register.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPW()

            }
        })

        // 최소 닉네임 글자수 설정
        var testNickname = findViewById<TextInputLayout>(R.id.nickname_btn)

        fun nicknameLength():Boolean{
            if(nickname_input.length()<2) { // 닉네임의 길이가 2자 미만일 경우
                nickname_input.setTextColor(-65536)
                return false
            }
            else {
                nickname_input.setTextColor(R.color.black.toInt())
                return true
            }
        }

        // 닉네임 특수문자 불가
        val nicknameValidation = "^[ㄱ-ㅣ가-힣a-zA-Z0-9\\\\s]+\$"
        fun checkNickname():Boolean{
            var nickname = nickname_input.text.toString().trim()
            val p = Pattern.matches(nicknameValidation, nickname)
            if (p) {
                nickname_input.setTextColor(R.color.black.toInt())
                testNickname.error = null
                return true
            }
            else {
                nickname_input.setTextColor(-65536)
                testNickname.error = "특수문자는 사용할 수 없습니다."
                return false
            }
        }

        nickname_input.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) { // 닉네임 중복확인인
               var isOverlap:Boolean= false
                overlap.setOnClickListener {
                    if(checkNickname()&&nicknameLength())
                        if(isOverlap)
                            testNickname.error = "중복된 닉네임입니다."
                        else {
                            testNickname.error = null
                            overlap.setText("확인")
                            overlap.setTextColor(Color.parseColor("#000000"))
                            overlap.setBackgroundResource(R.drawable.input_info_button)
                        }
                }
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkNickname()  // 닉네임 특수문자
                nicknameLength() // 닉네임 길이
            }

        })

        registerUser.getUserPage().enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful()){
                    Log.d("login","success ${response}")
                }else{
                    Log.d("login","but ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("login","error:${t.message}")
            }

        })



        // 회원가입 버튼 눌렀을 때
        signup_btn.setOnClickListener {
            var email = email_register.text.toString()
            var passwd = passwd_register.text.toString()
            var name = name_input.text.toString()
            var nickname = nickname_input.text.toString()
            var phone = phone_number.text.toString()
            Log.d("info", "$email, $passwd, $name, $nickname, $phone")

        // 정보가 전부 입력됐는지 확인
            if (email.isEmpty() || passwd.isEmpty() || name.isEmpty() || nickname.isEmpty() || phone.isEmpty()) {   // 모든 항목이 입력되지 않았을 때
                Toast.makeText(this, "항목을 채워주세요", Toast.LENGTH_LONG).show()
                isExistBlank = true
            } else {
                isExistBlank = false
            }

            // 이메일 가져오는 코드
            val savedEmail: String = "0000@example.com"


            if(!isExistBlank) { // 공백 없는 상태에서
                if (email == savedEmail) // 이미 있는 이메일
                    dialog()
                if(!checkEmail()) // 이메일 형식
                    testEmail.error = "이메일 형식이 아닙니다."
                if(!nicknameLength()) // 닉네임 2자~6자
                    testNickname.error = "닉네임은 2자~6자이어야 합니다."
                if(overlap.text != "확인")
                    Toast.makeText(this, "닉네임 중복확인을 해주세요.", Toast.LENGTH_SHORT).show()
                else if(email!=savedEmail && checkEmail() &&nicknameLength() && checkNickname() && checkPW()) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    startActivity(goLogin)

                    val user = User("email", "passwd", "name", "nickname", "phone")
                    registerUser.getUser(user).enqueue(object: Callback<User>{
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if(response.isSuccessful()){
                                Log.d("login","success ${response}")
                            }else{
                                Log.d("login","but ${response.errorBody()}")
                            }
                        }
                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("login","error:${t.message}")
                        }

                    })
                }
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