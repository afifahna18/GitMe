package com.afifah.gitme.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afifah.gitme.R
import com.afifah.gitme.databinding.FragmentFollowersFollowingBinding
import com.afifah.gitme.view.adapter.UserAdapter
import com.afifah.gitme.view.viewModel.FollowersViewModel
import com.afifah.gitme.view.viewModel.FollowingViewModel

class FollowingFragment : Fragment() {

    private var _binding : FragmentFollowersFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(ProfileUserActivity.EXTRA_USERNAME).toString()

        _binding = FragmentFollowersFollowingBinding.bind(view)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUserFollowersFollowing.setHasFixedSize(true)
            rvUserFollowersFollowing.layoutManager = LinearLayoutManager(activity)
            rvUserFollowersFollowing.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setUserFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it!=null){
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_followers_following, container, false)
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}