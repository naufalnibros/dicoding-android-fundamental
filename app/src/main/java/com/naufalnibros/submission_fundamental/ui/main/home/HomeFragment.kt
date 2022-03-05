package com.naufalnibros.submission_fundamental.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.core.preference.datastores.DataStoreThemeUI
import com.naufalnibros.submission_fundamental.core.preference.datastores.UIMode
import com.naufalnibros.submission_fundamental.databinding.FragmentHomeBinding
import com.naufalnibros.submission_fundamental.ui.main.UserAdapter
import com.naufalnibros.submission_fundamental.utils.viewBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by inject()

    private val themeUI: DataStoreThemeUI by inject()

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
                R.id.action_darktheme -> {
                    lifecycleScope.launch {
                        themeUI.setThemeMode(UIMode.DARK)
                    }
                }
                R.id.action_lighttheme -> {
                    lifecycleScope.launch {
                        themeUI.setThemeMode(UIMode.LIGHT)
                    }
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

        themeUI.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
            setCheckedMode(it)
        }
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

    private fun setCheckedMode(uiMode: UIMode) {
        when (uiMode) {
            UIMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            UIMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}