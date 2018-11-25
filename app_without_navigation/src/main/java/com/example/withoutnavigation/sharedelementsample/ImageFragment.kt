package com.example.withoutnavigation.sharedelementsample

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.withoutnavigation.sharedelementsample.databinding.FragmentImageBinding
import com.example.withoutnavigation.sharedelementsample.uitl.GlideApp

class ImageFragment : Fragment() {

    private lateinit var binding: FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.change_image_transform)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postponeEnterTransition()

        arguments?.getString(KEY_IMAGE_URL).let { imageURL ->
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
    }

    companion object {
        private const val KEY_IMAGE_URL = "image_url"

        fun newInstance(imageURL: String) = ImageFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_IMAGE_URL, imageURL)
            }
        }
    }
}