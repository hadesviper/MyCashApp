package com.herald.mycashapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.herald.mycashapp.R
import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.databinding.FragmentHomeBinding
import com.herald.mycashapp.domain.models.UserDataModel
import com.herald.mycashapp.presentation.viewmodels.HomeViewModel
import com.herald.mycashapp.presentation.viewmodels.UserViewModel

class FragmentHome : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModelUser: UserViewModel by activityViewModels()
    private val viewModelHome: HomeViewModel by activityViewModels()
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var userData: UserDataModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Constants.statusBarVisibility(true,requireActivity())
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.run {
            viewModelUser = this@FragmentHome.viewModelUser

        }
        userData = viewModelUser.state.value?.userData
        userData?.apply {
            binding.test.text = "name: $name  \naddress: $userID \n longitude: $lng \n latitue: $lat"
            longitude = lng
            latitude  = lat
        }
        initializeCategories()
        initializePopular()
        initializeTrending()
        return binding.root
    }

    private fun initializeCategories(){
        viewModelHome.run {
            getCategories()
            stateCategories.observe(viewLifecycleOwner){
                it.run {
                    categories?.run {
                        Log.i("TAG", "initializeCategories: $this")
                    }
                     error?.run {
                         Constants.showErrorDialog(requireContext(),this)
                     }
                }
            }
        }
    }
    private fun initializePopular(){
        viewModelHome.run {
            getPopularSellers(latitude = latitude, longitude = longitude, filter = 1)
            statePopular.observe(viewLifecycleOwner){
                it.run {
                    popular?.run {
                        Log.i("TAG", "initializePopular: $this")
                    }
                     error?.run {
                         Constants.showErrorDialog(requireContext(),this)
                     }
                }
            }
        }
    }
    private fun initializeTrending(){
        viewModelHome.run {
            getTrendingSellers(latitude = latitude, longitude = longitude, filter = 1)
            stateTrending.observe(viewLifecycleOwner){
                it.run {
                    trending?.run {
                        Log.i("TAG", "initializeTrending: $this")
                    }
                     error?.run {
                         Constants.showErrorDialog(requireContext(),this)
                     }
                }
            }
        }
    }
}