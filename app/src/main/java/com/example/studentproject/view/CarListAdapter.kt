package com.example.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentproject.databinding.CarListItemBinding
import com.example.studentproject.model.Car

class CarListAdapter(val carList: ArrayList<Car>):
    RecyclerView.Adapter<CarListAdapter.CarViewHolder>(){
    class CarViewHolder(val binding:CarListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.txtCarModel.text = carList[position].model
        holder.binding.txtCarYear.text = carList[position].year
        val features = carList[position].feature
        holder.binding.txtCarFeatures.text = features?.joinToString(", ") ?: ""
        val specs = carList[position].specs
        holder.binding.txtCarSpecs.text = specs?.toString() ?: ""
    }

    override fun getItemCount(): Int {
        return carList.size
    }
    fun updateCarList(newCarList: ArrayList<Car>){
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged() //untuk memberitahu data telah ter updated
    }
}