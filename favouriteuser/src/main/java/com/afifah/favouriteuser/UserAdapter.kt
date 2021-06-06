package com.afifah.favouriteuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afifah.favouriteuser.databinding.ItemUserHomeBinding
import com.squareup.picasso.Picasso

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<UserData>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setList(users: ArrayList<UserData>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemUserHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserData){

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Picasso.get()
                    .load(user.avatar_url)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(imgUserHome)
                tvUserNameHome.text = user.login
                tvUserLinkHome.text = user.html_url
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

    interface OnItemClickCallback{
        fun onItemClicked(data: UserData)
    }
}







