package com.example.gikhub

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gikhub.navigation.BuyFragment
import com.example.gikhub.navigation.EatFragment
import com.example.gikhub.navigation.TalkFragment
import com.example.gikhub.navigation.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
//            R.id.action_home ->{
//                var homeFragment= HomeFragment()
//                supportFragmentManager.beginTransaction().replace(R.id.main_content,homeFragment).commit()
//                return true
//            }
            R.id.action_buy ->{
                var buyFragment= BuyFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,buyFragment).commit()
                return true
            }
            R.id.action_eat ->{
                var eatFragment= EatFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,eatFragment).commit()
                return true
            }
            R.id.action_mypage ->{
                var userFragment= UserFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,userFragment).commit()
                return true
            }
            R.id.action_talk ->{
                var talkFragment= TalkFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,talkFragment).commit()
                return true
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_fragment)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        //set default screen
        bottom_navigation.selectedItemId = R.id.action_home
    }

}

