package com.example.drugiden

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.drugiden.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initInstances()
    }

    fun initInstances(){

        supportFragmentManager.beginTransaction()
                .add(R.id.mainActivity_content_container, MainFragment.newInstance())
                .commit()
    }
}
