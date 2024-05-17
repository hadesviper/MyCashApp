package com.herald.mycashapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.databinding.FragmentSignUpBinding
import com.herald.mycashapp.presentation.viewmodels.UserViewModel

class FragmentSignUp : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.resetState()
        initializeUI()
        initializeViewModel()
        return binding.root
    }

    private fun initializeUI(){
        Constants.statusBarVisibility(false,requireActivity())
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        binding.run {
            viewModel = this@FragmentSignUp.viewModel
            btnGoToLogIn.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        Glide.with(this)
            .load(R.drawable.brand)
            .into(binding.imgLogo)
    }

    private fun initializeViewModel(){
        viewModel.state.observe(viewLifecycleOwner) {
            it.run {
                isLoading.run {
                    binding.loading.visibility = if (this) View.VISIBLE else View.GONE
                }
                userData?.run {
                    findNavController().navigate(R.id.action_fragmentSignUp_to_fragmentHome)
                }
                error?.run {
                    Constants.showErrorDialog(requireContext(), this)
                }
            }
        }
    }
}