package org.techtowm.nagging.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.set_repeat.*
import org.techtowm.nagging.R

class SetRepeat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_repeat)

        val cb1:CheckBox= findViewById(R.id.mon);
        val cb2:CheckBox= findViewById(R.id.tue);
        val cb3:CheckBox= findViewById(R.id.wed);
        val cb4:CheckBox= findViewById(R.id.thu);
        val cb5:CheckBox= findViewById(R.id.fri);
        val cb6:CheckBox= findViewById(R.id.sat);
        val cb7:CheckBox= findViewById(R.id.sun);


        val b: Button = findViewById(R.id.save);
        val every:Button=findViewById(R.id.button);
        val week:Button=findViewById(R.id.button2);
        val weekend:Button=findViewById(R.id.button3);
        val tv: TextView = findViewById(R.id.textView2);

        b.setOnClickListener { view ->
            var result:String = "";  // 결과를 출력할 문자열  ,  항상 스트링은 빈문자열로 초기화 하는 습관을 가지자
            if (cb1.isChecked() == true) result += cb1.getText().toString();
            if (cb2.isChecked() == true) result += cb2.getText().toString();
            if (cb3.isChecked() == true) result += cb3.getText().toString();
            if (cb4.isChecked() == true) result += cb4.getText().toString();
            if (cb5.isChecked() == true) result += cb5.getText().toString();
            if (cb6.isChecked() == true) result += cb6.getText().toString();
            if (cb7.isChecked() == true) result += cb7.getText().toString();
            tv.setText("선택결과:" + result);
            val intent=Intent(this, SetAlarm::class.java)
            startActivity(intent)
        }
        every.setOnClickListener{ view ->
            cb1.setChecked(true);
            cb2.setChecked(true);
            cb3.setChecked(true);
            cb4.setChecked(true);
            cb5.setChecked(true);
            cb6.setChecked(true);
            cb7.setChecked(true);
        }
        week.setOnClickListener{ view ->
            cb1.setChecked(true);
            cb2.setChecked(true);
            cb3.setChecked(true);
            cb4.setChecked(true);
            cb5.setChecked(true);

            cb6.setChecked(false);
            cb7.setChecked(false);
        }
        weekend.setOnClickListener{ view ->
            cb1.setChecked(false);
            cb2.setChecked(false);
            cb3.setChecked(false);
            cb4.setChecked(false);
            cb5.setChecked(false);

            cb6.setChecked(true);
            cb7.setChecked(true);
        }
    }
}