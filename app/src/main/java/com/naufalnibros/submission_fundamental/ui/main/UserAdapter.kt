package com.naufalnibros.submission_fundamental.ui.main

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.ItemEmptyBinding
import com.naufalnibros.submission_fundamental.databinding.ItemUserBinding
import com.naufalnibros.submission_fundamental.repository.user.User


class UserAdapter(private val itemClickListener: (User) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<User> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> ItemViewHolder(
                ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> EmptyViewHolder(
                ItemEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bindItem(list[position])
                holder.binding.root.setOnClickListener {
                    itemClickListener.invoke(list[position])
                }
            }
            is EmptyViewHolder -> {
            }
        }
    }

    override fun getItemViewType(position: Int) = if (list.isEmpty()) 0 else 1

    override fun getItemCount() = if (list.isEmpty()) 1 else list.size

    class ItemViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: User) {
            binding.info.text = item.htmlUrl
            binding.name.text = item.username

            Glide.with(binding.root.context)
                .load(item.avatar)
                .placeholder(R.mipmap.ic_github)
                .centerCrop()
                .into(binding.avatar)

            binding.info.setOnClickListener {
                CustomTabsIntent.Builder().build()
                    .launchUrl(binding.root.context, Uri.parse(item.htmlUrl))
            }
        }
    }

    class EmptyViewHolder(binding: ItemEmptyBinding): RecyclerView.ViewHolder(binding.root)
}