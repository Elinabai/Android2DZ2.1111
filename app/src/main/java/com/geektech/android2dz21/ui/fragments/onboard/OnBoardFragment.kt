package com.geektech.android2dz21.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geektech.android2dz21.R
import com.geektech.android2dz21.databinding.FragmentOnboardBinding
import com.geektech.android2dz21.ui.adapters.OnBoardViewPagerAdapters

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListeners()
    }

    private fun initialize() {
        binding.vpViewPager.adapter = OnBoardViewPagerAdapters(this@OnBoardFragment)
        binding.dostIndicator.attachTo(binding.vpViewPager)
    }

    private fun setUpListeners() = with(binding.vpViewPager) {
        binding.btnNext.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }
        binding.tvText.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
        binding.vpViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.btnNext.isVisible = true
                        binding.tvText.isVisible = false
                    }
                    1 -> {
                        binding.btnNext.isVisible = true
                        binding.tvText.isVisible = false
                    }
                    2 -> {
                        binding.btnNext.isVisible = false
                        binding.tvText.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
}