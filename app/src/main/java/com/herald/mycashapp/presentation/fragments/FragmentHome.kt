package com.herald.mycashapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.herald.mycashapp.R
import com.herald.mycashapp.common.Constants
import com.herald.mycashapp.databinding.FragmentHomeBinding
import com.herald.mycashapp.domain.models.UserDataModel
import com.herald.mycashapp.presentation.adapters.CategoriesAdapter
import com.herald.mycashapp.presentation.adapters.PopularAdapter
import com.herald.mycashapp.presentation.adapters.TrendingAdapter
import com.herald.mycashapp.presentation.viewmodels.HomeViewModel
import com.herald.mycashapp.presentation.viewmodels.UserViewModel

class FragmentHome : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModelUser: UserViewModel by activityViewModels()
    private val viewModelHome: HomeViewModel by activityViewModels()
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var userData: UserDataModel? = null
    private var loadingProgress = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializeUI()
        initializeCategories()
        initializePopular()
        initializeTrending()
        return binding.root
    }
    private fun initializeUI(){
        Constants.statusBarVisibility(true,requireActivity())
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        userData = viewModelUser.state.value?.userData
        userData?.run {
            longitude = lng
            latitude  = lat
            binding.userData = this
        }
    }
    private fun initializeCategories(){
        viewModelHome.run {
            getCategories()
            stateCategories.observe(viewLifecycleOwner){
                it.run {
                    categories?.run {
                        incrementProgress()
                        binding.rVCategories.adapter = CategoriesAdapter(this.data)
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
                        incrementProgress()
                        binding.rVPopular.adapter= PopularAdapter(this.data)
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
                        binding.rVTrending.adapter = TrendingAdapter(this.data)
                        incrementProgress()
                    }
                     error?.run {
                         Constants.showErrorDialog(requireContext(),this)
                     }
                }
            }
        }
    }

    private fun incrementProgress(){
        Log.i("TAG", "incrementProgress: $loadingProgress")
        if (loadingProgress == 2){
            binding.run {
                loading.visibility = View.GONE
                scrollView.visibility = View.VISIBLE
            }
        }else{
            loadingProgress++
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelHome.resetState()
    }
}