package com.naufalnibros.submission_fundamental.ui.main.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.FragmentFavoriteBinding
import com.naufalnibros.submission_fundamental.ui.main.UserAdapter
import com.naufalnibros.submission_fundamental.utils.viewBinding
import org.koin.android.ext.android.inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)

    private val viewModel: FavoriteViewModel by inject()

    private val adapter = UserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ListFavoriteState.OnSuccess -> {
                    adapter.setList(it.list)
                }
                is ListFavoriteState.OnError -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                ListFavoriteState.OnLoading -> {

                }
            }
        }

    }

}