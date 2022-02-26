package com.naufalnibros.submission_fundamental.ui.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.naufalnibros.submission_fundamental.R
import com.naufalnibros.submission_fundamental.databinding.FragmentProfileBinding
import com.naufalnibros.submission_fundamental.utils.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}