package com.example.githubuserapi.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserapi.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = getString(R.string.detail_title)

        val pagerAdapter = PagerAdapter(this, supportFragmentManager)
        view_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

    }
}