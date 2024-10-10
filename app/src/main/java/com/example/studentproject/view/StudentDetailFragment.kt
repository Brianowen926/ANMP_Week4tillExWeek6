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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var viewModel:DetailViewModel
    private lateinit var student:Student

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
        student = Student(id, name, bod, phone,null)

        //munculin notifikasi + rxjava
        binding.btnUpdate.setOnClickListener{
            Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    MainActivity.showNotification("Coba", "Bla bla", R.drawable.ic_launcher_foreground)
                }
        }
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