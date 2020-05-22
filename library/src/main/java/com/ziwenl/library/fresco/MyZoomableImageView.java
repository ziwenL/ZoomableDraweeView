package com.ziwenl.library.fresco;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.ziwenl.library.fresco.zoomable.DoubleTapGestureListener;
import com.ziwenl.library.fresco.zoomable.ZoomableDraweeView;

/**
 * PackageName : com.ziwenl.library.fresco
 * Author : Ziwen Lan
 * Date : 2020/5/21
 * Time : 16:46
 * Introduction :
 * 通过 Fresco Demo 自带的 ZoomableDraweeView 调整实现可缩放的 ImageView
 * <p>
 * 默认实现双击放大/缩小功能
 * <p>
 * 提供设置点击事件的回调监听
 */
public class MyZoomableImageView extends ZoomableDraweeView {

    private DoubleTapGestureListener mDoubleTapGestureListener;

    public MyZoomableImageView(Context context) {
        super(context);
        init();
    }

    public MyZoomableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyZoomableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //实现双击放大/缩小功能
        mDoubleTapGestureListener = new DoubleTapGestureListener(this);
        setTapListener(mDoubleTapGestureListener);
    }


    /**
     * 资源转 uri
     */
    private Uri imageTranslateUri(@DrawableRes int resId) {
        Resources r = getContext().getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));
    }

    /**
     * 填充显示资源图片
     */
    public void setImageRes(@DrawableRes int resId) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageTranslateUri(resId))
                .build();
        setControllers(controller, null);
    }

    /**
     * 填充显示网络图片
     */
    public void setImageUrl(String imageUrl) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUrl)
                .build();
        setControllers(controller, null);
    }

    /**
     * 重写覆盖实现点击事件的回调监听
     */
    @Override
    public void setOnClickListener(@Nullable final View.OnClickListener onClickListener) {
        DoubleTapGestureListener.OnSingleClickListener onSingleClickListener = onClickListener == null ? null : new DoubleTapGestureListener.OnSingleClickListener() {
            @Override
            public void onSingleClick() {
                onClickListener.onClick(MyZoomableImageView.this);
            }
        };
        mDoubleTapGestureListener.setOnSingleClick(onSingleClickListener);
        this.setTapListener(mDoubleTapGestureListener);
    }
}
