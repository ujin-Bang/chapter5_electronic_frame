package com.restart.chapter5_electronic_frame

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PhotoFrameActivity: AppCompatActivity(){

    private val photoList = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_frame)

        getPhotoUriFromIntent()

    }

    private fun getPhotoUriFromIntent(){
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0 until size){
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it)) //String으로 넘어온 이미지를 다시 Uri타입으로 변환해서 포토리스트에 추가.
            }
        }
    }
}