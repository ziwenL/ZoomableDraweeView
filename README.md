# ZoomableDraweeView<a href="https://blog.csdn.net/lzw398756924/article/details/106275575" rel="nofollow">博客地址</a>
<h3 >基本介绍</h3>
<p>　　基于 <a href="https://github.com/facebook/fresco" rel="nofollow">Fresco</a> 的 <a href="https://github.com/facebook/fresco/tree/master/samples/zoomable/src/main/java/com/facebook/samples/zoomable" rel="nofollow">zoomable</a> 实现类 <a href="https://github.com/chrisbanes/PhotoView" rel="nofollow">PhotoView</a> 效果 </p>
<h3>显示效果</h3>
<img  src="https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cuaGVsbG9pbWcuY29tL2ltYWdlcy8yMDIwLzA1LzIyLzVlYzc0YTEwMDEzYTZfNWVjNzRjMzllMDViYTcxZDc5N2FhN2EyOTFlMDUuZ2lm?raw=true" alt="显示效果" />

<h3>变动及调整</h3>
<ul>
<li>
调整缩放倍数与 PhotoView 一致
</li>
<li>
View 重新可见时屏蔽重置缩放倍数
</li>
<li>
实现单击事件的监听
</li>
<li>
使用便捷性优化
</li>
</ul>

<h3>使用示例</h3>
<ul>
<li>
1.导入Fresco 库，并初始化
<pre>
dependencies {
    ......
    //Fresco 图片加载库
    api 'com.facebook.fresco:fresco:2.0.0'
}
</pre>
<pre>
//初始化
Fresco.initialize(this.applicationContext)
</pre>
</li>
<li>
2.复制 <a href="https://github.com/ziwenL/ZoomableDraweeView/tree/master/library/src/main/java/com/ziwenl/library/fresco" rel="nofollow">library/fresco/</a> 下的所有类到项目中
</li>
<li>
3. xml 中引用 MyZoomableImageView 
<pre>
<com.ziwenl.library.fresco.MyZoomableImageView
    android:id="@+id/iv_picture"
    android:layout_width="match_parent"
    app:progressBarAutoRotateInterval="1000"
    app:progressBarImage="@mipmap/loading"
    android:layout_height="match_parent" />
</pre>
</li>
<li>
4.填充图片及属性设置
<pre>
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
</pre>
</li>
</ul>

<h3>About Me<h3>
<ul>
<li>
<p>Email: ziwen.lan@foxmail.com</p>
</li>
</ul>
