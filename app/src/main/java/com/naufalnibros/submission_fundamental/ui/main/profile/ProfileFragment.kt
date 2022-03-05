package com.naufalnibros.submission_fundamental.ui.main.profile

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.FragmentProfileBinding
import com.naufalnibros.submission_fundamental.ui.main.profile.tab.ProfileTabPagerAdapter
import com.naufalnibros.submission_fundamental.utils.setColortint
import com.naufalnibros.submission_fundamental.utils.viewBinding
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val user by lazy {
        ProfileFragmentArgs.fromBundle(requireArguments()).user
    }

    private val viewModel: ProfileViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        with(binding.link) {
            text = user.htmlUrl
            setOnClickListener {
                CustomTabsIntent.Builder().build()
                    .launchUrl(requireContext(), Uri.parse(user.htmlUrl))
            }
        }

        Glide.with(binding.root.context)
            .load(user.avatar)
            .fitCenter()
            .into(binding.avatar)

        binding.containerContent.viewPager.adapter =
            ProfileTabPagerAdapter(user.username, childFragmentManager, lifecycle)

        TabLayoutMediator(
            binding.containerContent.tabLayout,
            binding.containerContent.viewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> "Follower"
                else -> "Following"
            }
        }.attach()

        with(user.username) {
            viewModel.detail(this)
            viewModel.find(this)
        }

        viewModel.state.observe(viewLifecycleOwner, stateObserver)
        viewModel.favoriteState.observe(viewLifecycleOwner, favoriteObserver)
    }

    private val stateObserver = Observer<ProfileState> {
        binding.root.visibility = if (it == ProfileState.OnLoading) {
            View.GONE
        } else {
            View.VISIBLE
        }

        when (it) {
            ProfileState.OnLoading -> {
            }
            is ProfileState.OnSuccess -> {
                binding.nama.text = it.user.name
                binding.alamat.text =
                    if (it.user.alamat.isNullOrBlank()) "User Tidak Punya Alamat" else it.user.alamat
            }
            is ProfileState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                binding.nama.text = "-"
                binding.alamat.text = "-"
            }
        }
    }

    private val favoriteObserver = Observer<FavoriteState> {
        when (it) {
            FavoriteState.NotFound -> {
                binding.fab.setColortint(R.color.grey_light)
                binding.fab.setOnClickListener {
                    viewModel.favorite(user)
                }
            }
            FavoriteState.OnSaved -> {
                binding.fab.setColortint(R.color.grey_dark)
                binding.fab.setOnClickListener {
                    viewModel.delete(user.username)
                }
            }
            is FavoriteState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}