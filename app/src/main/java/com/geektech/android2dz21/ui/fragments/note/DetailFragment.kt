package com.geektech.android2dz21.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.android2dz21.App
import com.geektech.android2dz21.R
import com.geektech.android2dz21.databinding.FragmentDetailBinding
import com.geektech.android2dz21.models.NoteModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var backgroundColor = "#1E1E1E"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendData()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btnImage.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_noteFragment)
        }
        btnBlack2.setOnClickListener {
            backgroundColor = "#1E1E1E"
        }
        btnFashionable.setOnClickListener {
            backgroundColor = "#EBE4C9"
        }
        btnBurgundy.setOnClickListener {
            backgroundColor = "#571818"
        }
    }

    private fun sendData() = with(binding) {
        btnMaterialOk.setOnClickListener {

            val line = etLine.text.toString()
            val number = tvNumber.text.toString()
            val time = etTime.text.toString()

            App.appDataBase?.noteDao()
                ?.insert(NoteModel(line, number, time, color = backgroundColor))

            findNavController().navigateUp()
        }
    }
}