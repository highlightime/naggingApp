
package org.techtowm.nagging.ui.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.techtowm.nagging.R;

public class AlarmList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list);

        TextView titleView = (TextView) findViewById(R.id.textView2);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title from tb_alarm "
                + "order by _id desc limit 1", null);
        while (cursor.moveToNext()) {
            titleView.setText(cursor.getString(0));
        }
        db.close();
    }
}