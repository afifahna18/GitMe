package com.afifah.gitme.broadcastreceiver

import android.content.Context

class AlarmPreference(context: Context) {

    companion object {
        const val PREFERENCE_NAME = "alarm_pref"
        private const val ALARM = "isAlarm"
    }

    private val alarm = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: AlarmData){
        val editor = alarm.edit()
        editor.putBoolean(ALARM, value.isAlarm)
        editor.apply()
    }

    fun getReminder(): AlarmData {
        val model = AlarmData()
        model.isAlarm = alarm.getBoolean(ALARM, false)
        return model
    }
}

















