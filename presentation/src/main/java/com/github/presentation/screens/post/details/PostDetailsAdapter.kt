package com.github.presentation.screens.post.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.domain.enteties.Post
import com.github.presentation.R
import com.github.presentation.image.loading.AvatarLoader

class PostDetailsAdapter(
        val post: Post,
        private val layoutInflater: LayoutInflater,
        private val avatarLoader: AvatarLoader
) : RecyclerView.Adapter<PostDetailVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailVH {
        return when (ViewHolderType.values()[viewType]) {
            ViewHolderType.HEADER -> {
                val view = layoutInflater.inflate(R.layout.post_details_header_item, parent, false)
                HeaderViewHolder(view)
            }
            ViewHolderType.COMMENT -> {
                val view = layoutInflater.inflate(R.layout.comment_item, parent, false)
                CommentViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = post.comments.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ViewHolderType.HEADER.ordinal else ViewHolderType.COMMENT.ordinal
    }

    override fun onBindViewHolder(holder: PostDetailVH, position: Int) {
        when (holder) {
            is CommentViewHolder -> {
                val comment = post.comments[position - 1]
                holder.authorName.text = comment.name
                holder.textBody.text = comment.body
            }
            is HeaderViewHolder -> {
                holder.authorNameView.text = post.userName
                holder.titleView.text = post.postTitle
                holder.bodyTextView.text = post.postBody
                avatarLoader.loadAvatar(post.userName, holder.avatarImageView)
            }
        }
    }

    private enum class ViewHolderType {
        HEADER, COMMENT
    }
}

abstract class PostDetailVH(view: View) : RecyclerView.ViewHolder(view)

class HeaderViewHolder(view: View) : PostDetailVH(view) {
    val avatarImageView: ImageView = view.findViewById(R.id.image_avatar)
    val titleView: TextView = view.findViewById(R.id.text_title)
    val bodyTextView: TextView = view.findViewById(R.id.text_body)
    val authorNameView: TextView = view.findViewById(R.id.text_author_name)
}

class CommentViewHolder(view: View) : PostDetailVH(view) {
    val textBody: TextView = view.findViewById(R.id.text_body)
    val authorName: TextView = view.findViewById(R.id.text_author_name)
}