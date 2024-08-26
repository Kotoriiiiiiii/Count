package com.example.count

import android.content.res.ColorStateList
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.count.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).apply{ setContentView(this.root)}

        var Count: Int = 0

        val soundPool = SoundPool(                               // 2
            10, AudioManager.STREAM_MUSIC, 0
        )

        val soundId: Int = soundPool.load(this, R.raw.hikinikudesu, 1)  // 3


        // val hikinikuSound: MediaPlayer = MediaPlayer.create(this, R.raw.hikinikudesu)

        binding.countButton.setOnClickListener {
            Count = Count + 1

            binding.countText.text = Count.toString()
        }
        if (Count % 2 == 0) {
            binding.countText.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(0,0,255))

        }else if (Count % 2 == 1) {
            binding.countText.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(255,0,0))

            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)

            // hikinikuSound.seekTo(0)

            // hikinikuSound.start()


        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}