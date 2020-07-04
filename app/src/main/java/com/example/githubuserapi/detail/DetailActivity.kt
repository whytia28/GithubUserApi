package com.example.githubuserapi.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapi.R
import com.example.githubuserapi.UserItems
import com.example.githubuserapi.model.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var userItems: UserItems? = null
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = getString(R.string.detail_title)

        val pagerAdapter = PagerAdapter(this, supportFragmentManager)
        view_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f

        showFollowerFollowing(userItems)

        userItems = intent.getParcelableExtra(EXTRA_USER) as UserItems
        tv_username.text = userItems!!.username
        userItems!!.avatar.apply {
            Glide.with(this@DetailActivity).load(userItems!!.avatar).into(avatar)
        }


        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        userItems?.username?.let { detailViewModel.setDetailUser(it) }
        detailViewModel.getDetailUser().observe(this@DetailActivity, Observer { userItems ->
            if (userItems != null) {
                tv_name.text = userItems.name
                tv_company.text = userItems.company
                tv_location.text = userItems.location
                Log.d("detail", "getDetail success")
            }
        })

    }

    private fun showFollowerFollowing(data: UserItems?) {

        val bundle = Bundle()
        val followersFragment = FollowersFragment()
        bundle.putString(FollowersFragment.EXTRA_FOLLOWERS, data?.username)
        followersFragment.arguments = bundle
        val followingFragment = FollowingFragment()
        bundle.putString(FollowingFragment.EXTRA_FOLLOWING, data?.username)
        followingFragment.arguments = bundle
    }
}