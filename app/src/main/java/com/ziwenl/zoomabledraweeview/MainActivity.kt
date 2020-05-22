package com.ziwenl.zoomabledraweeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Fresco.initialize(this.applicationContext)

        btn_start_url.setOnClickListener {
            PictureActivity.open(this, true)
        }

        btn_start_res.setOnClickListener {
            PictureActivity.open(this, false)
        }
    }
}
