package org.techtowm.nagging.ui.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.set_alarm.*
import org.techtowm.nagging.AlarmReceiver
import org.techtowm.nagging.DeviceBootReceiver
import org.techtowm.nagging.MainActivity
import org.techtowm.nagging.R
import java.text.SimpleDateFormat
import java.util.*


class SetAlarm: AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    var titleView: EditText? =null
    var addBtn: Button? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_alarm)
        val picker:TimePicker =findViewById(R.id.timePicker);
        picker.setIs24HourView(true);
        titleView =findViewById(R.id.editText1)
        addBtn =findViewById(R.id.button_save)

        button_repeat.setOnClickListener { view ->
            val intent= Intent(this, SetRepeat::class.java)
            startActivity(intent)
        }

        button_sound.setOnClickListener { view ->
            val intent= Intent(this, SetSound::class.java)
            startActivity(intent)
        }

        button_save.setOnClickListener { view ->
            var hour_24: Int = 0;
            var minute: Int = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hour_24 = picker.getHour()
                minute = picker.getMinute();
            };
            var mCalendar: Calendar = Calendar.getInstance();
            mCalendar.set(Calendar.HOUR_OF_DAY, hour_24);
            mCalendar.set(Calendar.MINUTE, minute);
            mCalendar.set(Calendar.SECOND, 0);
            // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
            // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
            if (mCalendar.before(Calendar.getInstance())) {
                mCalendar.add(Calendar.DATE, 1)
            }

            val currentDateTime: Date = mCalendar.getTime()
            val date_text =
                SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault())
                    .format(currentDateTime)
            Toast.makeText(
                applicationContext,
                date_text + "으로 알람이 설정되었습니다!",
                Toast.LENGTH_SHORT
            ).show()
            //  Preference에 설정한 값 저장
            val editor: SharedPreferences.Editor =
                getSharedPreferences("daily alarm", MODE_PRIVATE).edit()
            editor.putLong("nextNotifyTime", mCalendar.getTimeInMillis())
            editor.apply()
            diaryNotification(mCalendar)

            val title:String=titleView?.getText().toString()
            val helper = DBHelper(this)
            val db = helper.writableDatabase
            val sql: String="insert into tb_alarm values (null, '"+title+"');"
            db.execSQL(sql)
            db.close()
            val intent = Intent(this, AlarmList::class.java)
            startActivity(intent)
        }
    }

    fun diaryNotification(calendar:Calendar){
        val dailyNotify = true // 무조건 알람을 사용
        val pm = this.packageManager
        val receiver = ComponentName(this, DeviceBootReceiver::class.java)
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // 사용자가 매일 알람을 허용했다면
        if (dailyNotify) {
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }
            // 부팅 후 실행되는 리시버 사용가능하게 설정
            pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }
}