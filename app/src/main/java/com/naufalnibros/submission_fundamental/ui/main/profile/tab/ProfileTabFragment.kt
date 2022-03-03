package com.naufalnibros.submission_fundamental.ui.main.profile.tab

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.FragmentProfileTabBinding
import com.naufalnibros.submission_fundamental.ui.main.UserAdapter
import com.naufalnibros.submission_fundamental.utils.viewBinding
import org.koin.android.ext.android.inject

class ProfileTabFragment : Fragment(R.layout.fragment_profile_tab) {

    private val binding by viewBinding(FragmentProfileTabBinding::bind)

    private val viewModel: ProfileTabViewModel by inject()

    private val tabkey by lazy {
        requireArguments().getInt(TAB_KEY, 0)
    }

    private val username by lazy {
        requireArguments().getString(USERNAME_KEY, "")
    }

    private val adapter = UserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.list(tabkey, username)
        binding.swiperefresh.setOnRefreshListener { viewModel.list(tabkey, username) }

        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL))
        viewModel.state.observe(viewLifecycleOwner, observerState)
    }

    private val observerState = Observer<ProfileTabState> {
        binding.swiperefresh.isRefreshing = (it == ProfileTabState.OnLoading)
        when (it) {
            ProfileTabState.OnLoading -> {}
            is ProfileTabState.OnSuccess -> {
                adapter.setList(it.list)
            }
            is ProfileTabState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val TAB_KEY = "tabb"
        const val USERNAME_KEY = "usernamekey"
    }

}