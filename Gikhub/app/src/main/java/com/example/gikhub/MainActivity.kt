package com.example.gikhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import com.example.gikhub.navigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_talk.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.action_home ->{
                var homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,homeFragment).commit()
                return true
            }
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
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        //set default screen
        bottom_navigation.selectedItemId = R.id.action_home
    }

    fun changeFragment(index: Int){
        when(index){
            1->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_write, TalkWriteFragment()).commit()
            }
            2->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_view, TalkViewFragment()).commit()
            }
            3->{
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_main, TalkFragment()).commit()
            }
        }
    }

}
