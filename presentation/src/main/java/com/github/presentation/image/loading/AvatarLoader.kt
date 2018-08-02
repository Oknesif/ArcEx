package com.github.presentation.image.loading

import android.app.Activity
import android.content.res.Resources
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class AvatarLoader @Inject constructor(
        private val activity: Activity
) {

    private val avatarSize: Int by lazy {
        (Resources.getSystem().displayMetrics.density * 48).toInt()
    }

    private fun createAdorableUrl(id: String): String {
        return "$ADORABLE_BASE_URL/$avatarSize/$id}"
    }

    fun loadAvatar(id: String, into: ImageView) {
        val url = createAdorableUrl(id)
        Glide.with(activity).load(url).into(into)
    }
}

private const val ADORABLE_BASE_URL = "https://api.adorable.io/avatars"