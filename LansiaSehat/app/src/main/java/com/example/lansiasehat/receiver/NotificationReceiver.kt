package com.example.lansiasehat.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.lansiasehat.R
import com.example.lansiasehat.ui.rencana.DetailActivity
import com.example.lansiasehat.model.Todo

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val todo = intent.getParcelableExtra<Todo>("todo")
        if (todo != null) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationId = todo.id

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel("todo_channel", "Todo Notifications", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }

            val detailIntent = Intent(context, DetailActivity::class.java).apply {
                putExtra("todo", todo)
            }
            val pendingIntent = PendingIntent.getActivity(context, notificationId, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(context, "todo_channel")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(todo.title)
                .setContentText(todo.description)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(notificationId, notification)
        }
    }
}