package com.restart.chapter5_electronic_frame

import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat

class MainActivity : AppCompatActivity() {

    private val addPhotoButton:Button by lazy {
        findViewById(R.id.addPhotoButton)
    }

    private val startPhotoFrameModeButton: Button by lazy {
        findViewById(R.id.startPhotoFrameModeButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAddPhotoButton()
        initStartPhotoFrameModeButton()
    }

    private fun initAddPhotoButton(){

        addPhotoButton.setOnClickListener {
            when{
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED ->{
                    //todo 권한이 잘 부여되었을 때 갤러리에서 사진을 선택하는 기능
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    //todo 교육용 팝업 확인 후 권한 팝업을 띄우는 기능
                    showPermissionContextPopup()
                }
                else -> {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
                }
            }
        }
    }
    private fun showPermissionContextPopup(){
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("전자액자 앱에서 사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의하기", DialogInterface.OnClickListener { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            })
            .setNegativeButton("취소하기", DialogInterface.OnClickListener { _, _ ->

            })
            .create()
            .show()
    }


    private fun initStartPhotoFrameModeButton(){

    }
}
