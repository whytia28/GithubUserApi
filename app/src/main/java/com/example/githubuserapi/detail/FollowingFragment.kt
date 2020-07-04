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
import com.example.githubuserapi.adapter.FollowingAdapter
import com.example.githubuserapi.model.FollowingViewModel
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    companion object {
        const val EXTRA_FOLLOWING = "extra_following"
    }

    private lateinit var adapter: FollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowingAdapter()
        adapter.notifyDataSetChanged()

        showRecyclerView()

        followingViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)

        if (arguments != null) {
            val username = arguments?.getString(EXTRA_FOLLOWING)
            followingViewModel.setFollowing(username.toString())
        }
        followingViewModel.getFollowing().observe(viewLifecycleOwner, Observer { userItems ->
            if (userItems != null) {
                adapter.setData(userItems)
            }
        })
    }

    private fun showRecyclerView() {
        rv_following.layoutManager = LinearLayoutManager(context)
        rv_following.adapter = adapter
    }
}