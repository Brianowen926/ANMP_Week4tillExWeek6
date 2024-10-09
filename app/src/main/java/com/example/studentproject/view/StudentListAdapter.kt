package com.example.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentproject.databinding.StudentListItemBinding
import com.example.studentproject.model.Student

class StudentListAdapter(val studentList: ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
        class StudentViewHolder(val binding:StudentListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtId.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        val name = studentList[position].name //kasus ini kalo ada argumen di navigation
        val id = studentList[position].id //kasus ini kalo ada argumen di navigation
        val phone = studentList[position].phone //kasus ini kalo ada argumen di navigation
        val bod = studentList[position].bod //kasus ini kalo ada argumen di navigation
        holder.binding.btnDetail.setOnClickListener{
            val action = StudentListFragmentDirections.actionStudentDetail(name!!, id!!, phone!!, bod!!) //kasus ini kalo ada argumen di navigation
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged() //untuk memberitahu data telah ter updated
    }

}
