package com.geektech.android2dz21.ui.fragments.note

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.android2dz21.App
import com.geektech.android2dz21.App.Companion.preferenceHelper
import com.geektech.android2dz21.R
import com.geektech.android2dz21.databinding.FragmentNoteBinding
import com.geektech.android2dz21.interfaces.OnClickItem
import com.geektech.android2dz21.models.NoteModel
import com.geektech.android2dz21.ui.adapters.NoteAdapter

class NoteFragment() : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val list = ArrayList<NoteModel>()
    private val notAppAdapter = NoteAdapter(list, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setList()
        setupListeners()
        changeLayoutManager()
    }

    private fun changeLayoutManager() {
        if (preferenceHelper.saveBoolean) {
            binding.notResView.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.notResView.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initialize() {
        binding.notResView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notAppAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        btnMaterial.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_detailFragment)
        }
        gridManager.setOnClickListener {
            preferenceHelper.saveBoolean = false
            notResView.layoutManager = GridLayoutManager(requireContext() ,2)
            gridManager.isVisible = false
            linearManager.isVisible = true
        }
        linearManager.setOnClickListener {
            preferenceHelper.saveBoolean = true
            notResView.layoutManager = LinearLayoutManager(requireContext())
            gridManager.isVisible = true
            linearManager.isVisible = false
        }
    }

    private fun setList() {

        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner){
            notAppAdapter.setList(it as ArrayList<NoteModel>)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить")
            setPositiveButton("Да", DialogInterface.OnClickListener { dialog, which ->
                App.appDataBase?.noteDao()?.delete(noteModel)
            })
            setNegativeButton("Нет", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            show()
        }
        builder.create()
    }
}