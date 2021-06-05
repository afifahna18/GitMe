package com.afifah.gitme.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afifah.gitme.R
import com.afifah.gitme.databinding.FragmentFollowersFollowingBinding

class FollowingFragment : Fragment() {

    private var _binding : FragmentFollowersFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowersFollowingBinding.bind(view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_followers_following, container, false)
    }

}