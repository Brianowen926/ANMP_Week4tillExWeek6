package com.example.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentproject.model.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarViewModel(application: Application): AndroidViewModel(application) {
    fun refresh() {
        carLoadingLD.value = true
        carLoadErrorLD.value = false

        queue = Volley.newRequestQueue( getApplication()  )
        val url = "http://10.0.2.2/ANMP/cars.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object:TypeToken<List<Car>>(){}.type
                val result = Gson().fromJson<List<Car>>(it, sType)
                carsLD.value = result as ArrayList<Car>
                carLoadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                carLoadErrorLD.value = false
                carLoadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    val carsLD = MutableLiveData<ArrayList<Car>>()
    val carLoadErrorLD = MutableLiveData<Boolean>()
    val carLoadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTAG"
    private var queue:RequestQueue? = null
}