package com.example.githubuserapi.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapi.R
import com.example.githubuserapi.adapter.FollowerAdapter
import com.example.githubuserapi.model.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {

    companion object {
        const val EXTRA_FOLLOWERS = "extra_followers"
    }

    private lateinit var adapter: FollowerAdapter
    private lateinit var followersViewModel: FollowersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowerAdapter()
        adapter.notifyDataSetChanged()

        showRecyclerView()

        followersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        if (arguments != null) {
            val username = arguments?.getString(EXTRA_FOLLOWERS)
            followersViewModel.setFollowers(username.toString())
        }
        followersViewModel.getFollowers().observe(viewLifecycleOwner, Observer { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
            }
        })
    }

    private fun showRecyclerView() {
        rv_followers.layoutManager = LinearLayoutManager(context)
        rv_followers.adapter = adapter
    }
}