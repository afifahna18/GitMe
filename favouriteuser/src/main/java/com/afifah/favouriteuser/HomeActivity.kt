package com.afifah.favouriteuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afifah.favouriteuser.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)


        binding.apply {
            rvFavouriteUser.setHasFixedSize(true)
            rvFavouriteUser.layoutManager = LinearLayoutManager(this@HomeActivity)
            rvFavouriteUser.adapter = adapter
        }

        viewModel.setFavourite(this)

        viewModel.getFavouriteUser()?.observe(this, {
            if (it != null) {
                adapter.setList(it)
            }
        })
    }
}