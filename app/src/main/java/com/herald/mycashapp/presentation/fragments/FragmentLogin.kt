package com.herald.mycashapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.databinding.FragmentLoginBinding
import com.herald.mycashapp.presentation.viewmodels.UserViewModel

class FragmentLogin : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
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

    private fun initializeUI() {
        Constants.statusBarVisibility(false,requireActivity())
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.run {
            viewModel = this@FragmentLogin.viewModel
            btnGoToSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
            }
        }

        Glide.with(this)
            .load(R.drawable.brand)
            .into(binding.imgLogo)
    }

    private fun initializeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            it.run {
                isLoading.run {
                    binding.loading.visibility = if (this) View.VISIBLE else View.GONE
                }
                userData?.run {
                    Log.i("TAG", "initializeViewModel: invoked")
                    findNavController().navigate(R.id.action_fragmentLogIn_to_fragmentHome)
                }
                error?.run {
                    Constants.showErrorDialog(requireContext(), this)
                }
            }
        }
    }
}