package com.restart.chapter5_electronic_frame

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity: AppCompatActivity(){

    private val photoList = mutableListOf<Uri>()

    private var currentPosition = 0

    private var timer : Timer? = null


    private val photoImageView: ImageView by lazy {
        findViewById(R.id.photoImageView)
    }

    private val backgroundPhotoImageView: ImageView by lazy {
        findViewById(R.id.backgroundPhotoImageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_frame)

        getPhotoUriFromIntent()

        Log.d("PhotoFrame","onCreate!!")
    }

    private fun getPhotoUriFromIntent(){
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0 until size){
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it)) //String으로 넘어온 이미지를 다시 Uri타입으로 변환해서 포토리스트에 추가.
            }
        }
    }


    private fun startTimer(){
        //타이머 객체를 통해 5초마다 반복되도록.
      timer = timer(period = 5000){

          Log.d("PhotoFrame","스타트 타이머!! 5초마다 실행")

            //timer는 메인쓰레드가 아니므로 runOnUiThread내에서 작업하여 메인쓰레드로 변환해줌. 메인쓰레드가 아닌곳에서 UI를 건들면 앱이 죽는다.
            runOnUiThread {
                //몇번째 인댁스까지 돌았는지 전역변수로 저장 -> 지역변수로 다시 저장.
                val current = currentPosition
                //지금 이미지 뒤에 다음 이미지를 띄워야 하기 때문에 next포지션 지정. 마지막 이미지 일때는 첫번째 이미지로 돌아오게.
                val next = if (photoList.size <= currentPosition + 1) 0 else currentPosition +1 //축약문법

                backgroundPhotoImageView.setImageURI(photoList[current])

                photoImageView.alpha = 0f //포토이미지뷰의 투명도 0으로 주어 이미지뷰가 보이지 않게
                photoImageView.setImageURI(photoList[next])
                photoImageView.animate() // 0~1로 서서히 알파값이 바뀌게 애니메이션효과
                    .alpha(1.0f)
                    .setDuration(1000) //1초
                    .start()

                currentPosition = next
            }
        }
    }
    //바탕화면 보기 버튼 누른경우 등으로 앱이 실행되지 않을 때
    override fun onStop() {
        super.onStop()

        Log.d("PhotoFrame","onStop!! timer cancel")

        timer?.cancel() //타이머 종료

    }

    //온스타트 상태로 들온 경우 스타트 타이머 실행.
    override fun onStart() {
        super.onStart()

        Log.d("PhotoFrame","onStart!! timer Start")

        startTimer() // onCreate함수에서 startTimer()를 실행하면 한번밖에 실행되지 않기 때문에 onStart()로 startTimer()를 이동시켜줌.
    }

    //앱이 완전히 종료되었을 때 타이머 종료.
    override fun onDestroy() {
        super.onDestroy()

        Log.d("PhotoFrame","onDestroy!! timer cancel")

        timer?.cancel()
    }
}