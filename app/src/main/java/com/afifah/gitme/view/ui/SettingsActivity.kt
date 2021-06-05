package com.afifah.gitme.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.afifah.gitme.broadcastreceiver.AlarmPreference
import com.afifah.gitme.broadcastreceiver.AlarmReceiver
import com.afifah.gitme.databinding.ActivitySettingsBinding
import com.afifah.gitme.broadcastreceiver.AlarmData

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var alarm: AlarmData
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alarmPreference = AlarmPreference(this)
        binding.switchAlarm.isChecked = alarmPreference.getReminder().isAlarm

        alarmReceiver = AlarmReceiver()

        binding.apply {
            btnLanguage.setOnClickListener{
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }

        binding.switchAlarm.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this, "Repeating ALARM","05.37", "GITHUB REMINDER")
            }else{
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val alarmPreference = AlarmPreference(this)
        alarm = AlarmData()

        alarm.isAlarm = state
        alarmPreference.setReminder(alarm)
    }
}












