package org.techtowm.nagging

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.techtowm.nagging.ui.main.DBHelper
import org.techtowm.nagging.ui.main.SectionsPagerAdapter
import org.techtowm.nagging.ui.main.SetAlarm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Make your alarm", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val intent=Intent(this, SetAlarm::class.java)
            startActivity(intent)
        }
    }

}