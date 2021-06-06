package com.afifah.gitme.broadcastreceiver

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Message
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.afifah.gitme.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "01"
        private const val CHANNEL_NAME = "Alarm"
        const val MESSAGE = "message"
        private const val TIME_FORMAT = "HH:mm"
        const val TYPE = "type"
        private const val ID_ALARM = 101
    }

    override fun onReceive(context: Context, intent: Intent) {
        sendNotification(context)
    }

    private fun sendNotification(context: Context) {
        val intent = context?.packageManager.getLaunchIntentForPackage("com.afifah.gitme")

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("FIND YOUR FAVOURITE USER GITHUB")
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
    fun setRepeatingAlarm(context: Context, type: String, time: String, message: String){
        if (isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver:: class.java)
        intent.putExtra(MESSAGE, message)
        intent.putExtra(TYPE, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_ALARM, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
        Toast.makeText(context, "ALARM SUDAH DINYALAKAN", Toast.LENGTH_SHORT).show()
    }

    private fun isDateInvalid(time: String, timeFormat: String): Boolean {
        return try {
            val simpleDateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
            simpleDateFormat.isLenient = false
            simpleDateFormat.parse(time)
            false
        }catch (e: ParseException){
            true
        }
    }

    fun cancelAlarm(context: Context){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_ALARM
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0 )
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "ALARM SUDAH DIMATIKAN", Toast.LENGTH_SHORT).show()

    }
}

















