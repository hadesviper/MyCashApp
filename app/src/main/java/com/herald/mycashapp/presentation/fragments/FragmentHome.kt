package com.herald.mycashapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.herald.mycashapp.R
import com.herald.mycashapp.databinding.FragmentHomeBinding
import com.herald.mycashapp.databinding.FragmentLoginBinding
import com.herald.mycashapp.presentation.viewmodels.UserViewModel

class FragmentHome : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModelUser: UserViewModel by activityViewModels()
    private val viewModelCategories: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.run {
            viewModelUser = this@FragmentHome.viewModelUser
            viewModelCategories = this@FragmentHome.viewModelCategories
        }

        viewModelCategories.state.observe(viewLifecycleOwner) {
            it.userData?.let {
                Log.i("TAG", "onCreateView: Categories Success invoked $it")
            }
            it.error?.run {
                showErrorDialog(it.error)
            }
        }

        return binding.root
    }

    private fun showErrorDialog(message: String) {
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