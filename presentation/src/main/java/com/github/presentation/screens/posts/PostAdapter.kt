package com.github.presentation.screens.posts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.domain.enteties.Post
import com.github.presentation.R
import com.github.presentation.image.loading.AvatarLoader

class PostAdapter(
        val items: MutableList<Post> = mutableListOf(),
        private val avatarLoader: AvatarLoader,
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

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = items[position]
        holder.bodyText.text = item.body
        holder.view.setOnClickListener { itemClickListener(items[holder.adapterPosition]) }
        avatarLoader.loadAvatar(item.userId, holder.imageView)
    }
}

class PostViewHolder(
        val view: View,
        val bodyText: TextView = view.findViewById(R.id.text_body),
        val imageView: ImageView = view.findViewById(R.id.image_view)
) : RecyclerView.ViewHolder(view)