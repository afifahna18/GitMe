package com.afifah.gitme.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.afifah.gitme.databinding.ActivityProfileUserBinding
import com.afifah.gitme.view.adapter.SectionPagerAdapter
import com.afifah.gitme.view.viewModel.ProfileUserViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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
                    Glide.with(this@ProfileUserActivity)
                        .load(it.photo)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(imageAvatar)
                }
            }
        })
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabsFollow.setupWithViewPager(viewPager)
        }
    }









}










