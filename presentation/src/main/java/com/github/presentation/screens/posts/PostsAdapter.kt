package com.github.presentation.screens.posts

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.domain.enteties.Post
import com.github.presentation.R
import com.github.presentation.image.loading.AvatarLoader

class PostsAdapter(
        val items: MutableList<Post> = mutableListOf(),
        private val layoutInflater: LayoutInflater,
        private val itemClickListener: (post: Post) -> Unit
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): PostViewHolder {
        val view = layoutInflater.inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = items[position]
        holder.subtitleText.text = "${item.userName}   ${item.comments.size}"
        holder.titleText.text = item.postTitle
        holder.bodyText.text = item.postBody
        holder.view.setOnClickListener { itemClickListener(items[holder.adapterPosition]) }
    }
}

class PostViewHolder(
        val view: View,
        val bodyText: TextView = view.findViewById(R.id.text_body),
        val titleText: TextView = view.findViewById(R.id.text_title),
        val subtitleText: TextView = view.findViewById(R.id.text_subtitle)
) : RecyclerView.ViewHolder(view)