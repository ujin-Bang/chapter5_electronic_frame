package com.restart.chapter5_electronic_frame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val addPhotoButton:Button by lazy {
        findViewById(R.id.addPhotoButton)
    }

    private val startPhotoFramModeButton: Button by lazy {
        findViewById(R.id.startPhotoFrameModeButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAddPhotoButton()
        initStartPhotoFrameModeButton()
    }

    private fun initAddPhotoButton(){

    }

    private fun initStartPhotoFrameModeButton(){

    }
}
