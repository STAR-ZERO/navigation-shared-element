package com.example.withnavigation.sharedelementsample

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.withnavigation.sharedelementsample.databinding.FragmentGridBinding

class GridFragment : Fragment() {

    private lateinit var binding: FragmentGridBinding

    // Show Activity
    private val adapter = GridAdapter(this::clickItemActivity)

    // Show Fragment
//    private val adapter = GridAdapter(this::clickItemFragment)

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

    private fun clickItemActivity(view: View) {
        val action = GridFragmentDirections.actionGridFragmentToImageActivity(view.transitionName) // transitionName == imageURL

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                view,
                view.transitionName
        )
        val extras = ActivityNavigator.Extras.Builder().setActivityOptions(options).build()

        findNavController().navigate(action, extras)
    }

    private fun clickItemFragment(view: View) {
        exitTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.change_image_transform)
        val action = GridFragmentDirections.actionGridFragmentToImageFragment(view.transitionName) // transitionName == imageURL
        val extra = FragmentNavigatorExtras(view to view.transitionName)
        findNavController().navigate(action, extra)
    }
}
