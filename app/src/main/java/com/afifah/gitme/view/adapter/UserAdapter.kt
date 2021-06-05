package com.afifah.gitme.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afifah.gitme.R
import com.afifah.gitme.databinding.ItemUserHomeBinding
import com.afifah.gitme.room.usermodel.UserData
import com.squareup.picasso.Picasso

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<UserData>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

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
                    .load(user.photos)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
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

    interface OnItemClickCallback{
        fun onItemClicked(data: UserData)
    }
}







