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
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.databinding.FragmentLoginBinding
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
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        binding.run {
            viewModel = this@FragmentSignUp.viewModel
            btnGoToLogIn.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentSignUp_to_fragmentLogIn)
            }
        }

        Glide.with(this)
            .load(R.drawable.brand)
            .into(binding.imgLogo)

        viewModel.resetState()
        viewModel.state.observe(viewLifecycleOwner) {
            it.userData?.let {
                findNavController().navigate(R.id.action_fragmentSignUp_to_fragmentHome)
            }
            it.error?.run {
                showErrorDialog(it.error)
            }
        }

        return binding.root
    }

    private fun showErrorDialog( message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage("Error message: $message")
            .setTitle("An error has occurred")
            .setNegativeButton("Dismiss!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
            }
    }
}