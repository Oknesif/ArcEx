package com.github.presentation.image.loading

import android.content.res.Resources
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.scopes.FragmentScope
import javax.inject.Inject

@FragmentScope
class AvatarLoader @Inject constructor(
        private val fragment: Fragment
) {

    private val avatarSize: Int by lazy {
        (Resources.getSystem().displayMetrics.density * IMAGE_SIZE).toInt()
    }

    private fun createAdorableUrl(id: String): String {
        return "$ADORABLE_BASE_URL/$avatarSize/$id}"
    }

    fun loadAvatar(id: String, into: ImageView) {
        val url = createAdorableUrl(id)
        Glide.with(fragment).load(url).into(into)
    }
}

private const val IMAGE_SIZE = 48
private const val ADORABLE_BASE_URL = "https://api.adorable.io/avatars"