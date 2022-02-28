package com.naufalnibros.submission_fundamental.ui.main.profile

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.FragmentProfileBinding
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

        with(binding.toolbar) {
            title = user.username
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        with(binding.containerContent.link) {
            text = user.htmlUrl
            setOnClickListener {
                CustomTabsIntent.Builder().build()
                    .launchUrl(requireContext(), Uri.parse(user.htmlUrl))
            }
        }

        viewModel.detail(user.username)

        Glide.with(binding.root.context)
            .load(user.avatar)
            .placeholder(R.mipmap.ic_github)
            .centerCrop()
            .into(binding.avatar)

        binding.fab.setOnClickListener {
            Toast.makeText(requireContext(), "Soon Submission 3", Toast.LENGTH_LONG).show()
        }

        viewModel.state.observe(viewLifecycleOwner) {
            binding.containerContent.root.visibility = if (it == ProfileState.OnLoading) {
                View.GONE
            } else {
                View.VISIBLE
            }

            when (it) {
                ProfileState.OnLoading -> {
                }
                is ProfileState.OnSuccess -> {
                    binding.containerContent.nama.text = it.user.name
                    binding.containerContent.alamat.text = if (it.user.alamat.isNullOrBlank()) "User Tidak Punya Alamat" else it.user.alamat
                }
                is ProfileState.OnError -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding.containerContent.nama.text = "-"
                    binding.containerContent.alamat.text = "-"
                }
            }
        }
    }

}