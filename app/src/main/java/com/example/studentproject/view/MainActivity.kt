package com.example.studentproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.studentproject.R
import android.Manifest //dari W7 hlm 23
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studentproject.util.createNotificationChanel

class MainActivity : AppCompatActivity() {
    init { //init = constructor menjalankan program saat object dibuat
        instance = this //berfungsi sebagai context. Dalam kasus ini kita buat notification dan notification butuh context
    }

    companion object {
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int){
            val chanelId = "${instance?.packageName}" + "${instance?.getString(R.string.app_name)}"

            val builder = NotificationCompat.Builder(instance!!.applicationContext, chanelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            //CODE UNTUK REQUEST NOTIFICATION
            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)
            if(ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS)!=
                PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
                return
            }
            notificationManager.notify(1001, builder.build())
        }
    }

    override fun onRequestPermissionsResult( //fun yg dipanggil kalo user klik allow/deny
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                createNotificationChanel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true, getString(R.string.app_name),
                    "App channel")
                Log.d("Permission", "granted")
            }else{
                Log.d("Permission", "deny")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChanel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

    }
}