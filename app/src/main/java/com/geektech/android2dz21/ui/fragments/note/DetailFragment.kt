package com.geektech.android2dz21.ui.fragments.note

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.geektech.android2dz21.App
import com.geektech.android2dz21.R
import com.geektech.android2dz21.databinding.FragmentDetailBinding
import com.geektech.android2dz21.models.NoteModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendData() = with(binding) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM")
        val formatted = current.format(formatter)
        binding.tvData.text = formatted

        btnMaterialOk.setOnClickListener {
            val line = etLine.text.toString()
            val number = etTime.text.toString()
            val data = tvNumber.text.toString()

            App.appDataBase?.noteDao()
                ?.insert(NoteModel(line,formatted ,number, data, color = backgroundColor))

            findNavController().navigateUp()
        }
    }
}