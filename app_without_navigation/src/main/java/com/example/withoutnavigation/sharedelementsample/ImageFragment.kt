package com.example.withoutnavigation.sharedelementsample

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.withoutnavigation.sharedelementsample.databinding.FragmentImageBinding

class ImageFragment: Fragment() {

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

        arguments?.getString(KEY_IMAGE_URL).let { imageURL ->
            binding.image.transitionName = imageURL
            binding.imageURL = imageURL
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