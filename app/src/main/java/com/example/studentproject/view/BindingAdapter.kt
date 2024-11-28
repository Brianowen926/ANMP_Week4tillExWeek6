package com.example.studentproject.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.lang.Exception

@BindingAdapter("android:imageUrl") //hati hati. karena bindingAdapter ini attack semua, misal bingdingadapter ke imageview, maka semua imageview akan kena
fun loadPhoto(imageView: ImageView, url:String?) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception ->
        exception.printStackTrace()
    }
    picasso.build().load(url).into(imageView, object:com.squareup.picasso.Callback{
        override fun onSuccess() {
            imageView.visibility = View.VISIBLE
        }

        override fun onError(e: Exception?) {
            TODO("Not yet implemented")
        }

    })
}