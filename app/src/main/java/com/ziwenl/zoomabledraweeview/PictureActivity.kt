package com.ziwenl.zoomabledraweeview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.ziwenl.library.fresco.zoomable.DoubleTapGestureListener
import kotlinx.android.synthetic.main.activity_picture.*

/**
 * PackageName : com.ziwenl.zoomabledraweeview
 * Author : Ziwen Lan
 * Date : 2020/5/21
 * Time : 17:20
 * Introduction :
 */
class PictureActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_KEY_IS_URL = "isUrl"

        fun open(context: Activity, isUrl: Boolean) {
            val intent = Intent(context, PictureActivity::class.java)
            intent.putExtra(EXTRA_KEY_IS_URL, isUrl)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val isUrl = intent.getBooleanExtra(EXTRA_KEY_IS_URL, false)

        if (isUrl) {
            //加载网络图片
            iv_picture.setImageUrl("https://www.helloimg.com/images/2020/05/18/splash_bgb5d0ea1e3c8a4cc8.jpg")
        } else {
            //加载本地资源图片
            iv_picture.setImageRes(R.mipmap.splash_bg)
        }

        //保存缩放状态 -- 当前 view 重新可见时不重置缩放倍数
        iv_picture.setSaveScale(true)

        //点击事件
        iv_picture.setOnClickListener {
            Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show()
        }
    }
}