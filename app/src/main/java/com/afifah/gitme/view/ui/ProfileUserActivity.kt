package com.afifah.gitme.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.afifah.gitme.R
import com.afifah.gitme.databinding.ActivityProfileUserBinding
import com.afifah.gitme.view.adapter.SectionPagerAdapter
import com.afifah.gitme.view.viewModel.ProfileUserViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID ="extra_id"
        const val EXTRA_PHOTO ="extra_photo"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var binding: ActivityProfileUserBinding
    private lateinit var viewModel:ProfileUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val photo = intent.getStringExtra(EXTRA_PHOTO)
        val url = intent.getStringExtra(EXTRA_URL)


        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this).get(ProfileUserViewModel::class.java)

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

        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count>0){
                        binding.btnFavouriteUser.isChecked = true
                        isChecked = true
                    }else{
                        binding.btnFavouriteUser.isChecked = false
                        isChecked = false
                    }
                }
            }
        }

        binding.btnFavouriteUser.setOnClickListener {
            isChecked = !isChecked
            if (isChecked){
                viewModel.addToFavourite(username, id, photo, url)
            } else {
                viewModel.removeFromFavourite(id)
            }
            binding.btnFavouriteUser.isChecked = isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabsFollow.setupWithViewPager(viewPager)
        }
    }





}










