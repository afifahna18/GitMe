package com.afifah.gitme.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.afifah.gitme.R
import com.afifah.gitme.databinding.ActivityProfileUserBinding
import com.afifah.gitme.view.adapter.SectionPagerAdapter
import com.afifah.gitme.view.viewModel.ProfileUserViewModel
import com.squareup.picasso.Picasso

class ProfileUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityProfileUserBinding
    private lateinit var viewModel:ProfileUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ProfileUserViewModel::class.java)

        username?.let { viewModel.setUserProfile(it) }
        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it.fullName
                    tvUsername.text = it.username
                    tvCompany.text = it.company
                    tvLocation.text = it.location
                    tvBio.text = it.bio
                    tvFollowersDetail.text = it.followers.toString()
                    tvFollowingDetail.text = it.following.toString()
                    tvRepositoryDetail.text = it.repository.toString()
                    Picasso.get()
                        .load(it.photo)
                        .placeholder(R.drawable.ic_person)
                        .error(R.drawable.ic_person)
                        .into(imageAvatar)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabsFollow.setupWithViewPager(viewPager)
        }
    }









}










