package com.afifah.gitme.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afifah.gitme.R
import com.afifah.gitme.databinding.ActivityFavouriteBinding
import com.afifah.gitme.room.usermodel.FavouriteUserData
import com.afifah.gitme.room.usermodel.UserData
import com.afifah.gitme.view.adapter.UserAdapter
import com.afifah.gitme.view.viewModel.FavouriteViewModel

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserData) {
                Intent(this@FavouriteActivity, ProfileUserActivity::class.java).also {
                    it.putExtra(ProfileUserActivity.EXTRA_USERNAME, data.username)
                    it.putExtra(ProfileUserActivity.EXTRA_ID, data.id)
                    it.putExtra(ProfileUserActivity.EXTRA_PHOTO, data.id)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvFavouriteUser.setHasFixedSize(true)
            rvFavouriteUser.layoutManager = LinearLayoutManager(this@FavouriteActivity)
            rvFavouriteUser.adapter = adapter
        }

        viewModel.getFavouriteUser()?.observe(this, {
            if(it != null){
                val list = mapList(it)
                adapter.setList(list)
            }
        })
    }

    private fun mapList(users: List<FavouriteUserData>): ArrayList<UserData> {
        val listUsers = ArrayList<UserData>()
        for (user in users){
            val userMapped = UserData(
                user.id,
                user.username,
                user.photo,
                user.url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }
}




























