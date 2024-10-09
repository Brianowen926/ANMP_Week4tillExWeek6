package com.example.studentproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentproject.databinding.FragmentCarListBinding
import com.example.studentproject.viewmodel.CarViewModel


class CarListFragment : Fragment() {
    private lateinit var viewModel: CarViewModel
    private val carListAdapter = CarListAdapter(arrayListOf())
    private lateinit var binding:FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = carListAdapter

        observeViewModel()

        binding.refreshLayoutCar.setOnRefreshListener {
            viewModel.refresh()
        }

    }

    fun observeViewModel() {
        viewModel.carsLD.observe(viewLifecycleOwner, Observer {
            carListAdapter.updateCarList(it)
        })
        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.carLoadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE

            }

        })
    }


}
