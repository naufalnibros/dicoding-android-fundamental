package com.naufalnibros.submission_fundamental.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.FragmentHomeBinding
import com.naufalnibros.submission_fundamental.ui.main.UserAdapter
import com.naufalnibros.submission_fundamental.utils.viewBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by inject()

    private val adapter = UserAdapter {
        findNavController().navigate(HomeFragmentDirections.toProfileFragment(it))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_favotire -> {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                    )
                }
                R.id.action_darkthem -> {

                }
            }
            false
        }

        binding.searchview.setOnQueryTextListener(onQueryTextListener)

        binding.swiperefresh.setOnRefreshListener { viewModel.refresh() }

        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.stateList.observe(viewLifecycleOwner, observerStateList)
    }

    private val observerStateList = Observer<HomeState> {
        binding.swiperefresh.isRefreshing = (it == HomeState.OnLoading)
        when (it) {
            is HomeState.OnSuccess -> {
                adapter.setList(it.list)
            }
            is HomeState.OnError -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
            HomeState.OnLoading -> {}
        }}

    private val onQueryTextListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.search(query ?: "")
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.search(newText ?: "")
            return false
        }
    }

}