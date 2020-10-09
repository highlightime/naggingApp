package org.techtowm.nagging.ui.main

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import org.techtowm.nagging.R


class SetSound: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_sound)
        val seekVolume: SeekBar = findViewById(R.id.seekbar);
        val audioManager =
            getSystemService(Context.AUDIO_SERVICE) as AudioManager
        var nMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        //var nCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val b: Button = findViewById(R.id.save);
        seekVolume.setMax(nMax);
        //seekVolume.setProgress(nCurrentVolumn);
        // Set a SeekBar change listener
        seekVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                //text_view.text = "Progress : $i"
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                   i, 0);
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
        b.setOnClickListener { view ->
            val intent= Intent(this, SetAlarm::class.java)
            startActivity(intent)
        }
    }
}
