package com.example.githubuserapi.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubuserapi.R

class FollowersFragment : Fragment() {

    private val ARG_SECTIEN_NUMBER = "section_number"

    fun newInstance(index: Int): FollowersFragment {
        val fragment = FollowersFragment()
        val bundle = Bundle()
        bundle.putInt(ARG_SECTIEN_NUMBER, index)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var index = 1
        if (arguments != null) {
            index = arguments?.getInt(ARG_SECTIEN_NUMBER, 0) as Int
        }
    }
}