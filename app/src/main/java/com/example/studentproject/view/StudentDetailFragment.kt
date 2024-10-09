package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.studentproject.R
import com.example.studentproject.databinding.FragmentStudentDetailBinding
import com.example.studentproject.databinding.FragmentStudentListBinding
import com.example.studentproject.model.Student
import com.example.studentproject.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name") //kalo pake argumen di nav
        val phone = arguments?.getString("phone") //kalo pake argumen di nav
        val id = arguments?.getString("id") //kalo pake argumen di nav
        val bod = arguments?.getString("bod") //kalo pake argumen di nav

        val student = Student(id, name, bod, phone, "") //kalo pake argumen di nav

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(student)

        obserViewModel()
    }

    fun obserViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.txtName.setText(it.name)
            binding.txtBOD.setText(it.bod)
            binding.txtPhone.setText(it.phone)
            binding.txtId.setText(it.id)
        })
    }

}