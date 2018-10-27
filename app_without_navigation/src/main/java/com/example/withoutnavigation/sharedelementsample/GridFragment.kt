package com.example.withoutnavigation.sharedelementsample

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.withoutnavigation.sharedelementsample.databinding.FragmentGridBinding

class GridFragment : Fragment() {

    private lateinit var binding: FragmentGridBinding

    private val adapter = GridAdapter(this::clickItem)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycler.adapter = adapter

        adapter.submitList(listOf(
                "https://images.pexels.com/photos/1383397/pexels-photo-1383397.jpeg?w=500",
                "https://images.pexels.com/photos/132694/pexels-photo-132694.jpeg?w=500",
                "https://images.pexels.com/photos/1423455/pexels-photo-1423455.jpeg?w=500",
                "https://images.pexels.com/photos/1251175/pexels-photo-1251175.jpeg",
                "https://images.pexels.com/photos/209037/pexels-photo-209037.jpeg",
                "https://images.pexels.com/photos/291528/pexels-photo-291528.jpeg",
                "https://images.pexels.com/photos/302478/pexels-photo-302478.jpeg",
                "https://images.pexels.com/photos/982612/pexels-photo-982612.jpeg"
        ))
    }

    private fun clickItem(view: View) {
        exitTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.change_image_transform)
        val fragment = ImageFragment.newInstance(view.transitionName)
        requireFragmentManager().beginTransaction()
                .addSharedElement(view, view.transitionName)
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit()
    }
}
