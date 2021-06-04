package com.afifah.gitme.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afifah.gitme.databinding.ItemUserHomeBinding
import com.afifah.gitme.room.usermodel.UserData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<UserData>()

    fun setList(users: ArrayList<UserData>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserData){
            binding.apply { Glide.with(itemView)
                .load(user.photos)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(imgUserHome)
                tvUserNameHome.text = user.username
                tvUserLinkHome.text = user.url
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}







