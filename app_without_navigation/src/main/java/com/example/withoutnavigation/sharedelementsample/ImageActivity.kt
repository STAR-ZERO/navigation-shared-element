package com.example.withoutnavigation.sharedelementsample

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.withoutnavigation.sharedelementsample.databinding.ActivityImageBinding
import com.example.withoutnavigation.sharedelementsample.uitl.GlideApp

class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image)

        window.enterTransition = null
        window.exitTransition = null

        postponeEnterTransition()

        val imageURL = intent.getStringExtra(EXTRA_IMAGE_URL)
        binding.image.transitionName = imageURL

        GlideApp.with(this)
                .load(imageURL)
                .dontAnimate()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(binding.image)
    }

    companion object {

        private const val EXTRA_IMAGE_URL = "image_url"

        fun createIntent(context: Context, imageURL: String) = Intent(context, ImageActivity::class.java).apply {
            putExtra(EXTRA_IMAGE_URL, imageURL)
        }
    }
}