package com.ziwenl.library.fresco.zoomable;

import android.graphics.PointF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Author : Ziwen Lan
 * Date : 2020/5/18
 * Time : 10:40
 * Introduction :
 * change：实现点击事件监听回调
 */
public class DoubleTapGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final int DURATION_MS = 300;
    private static final int DOUBLE_TAP_SCROLL_THRESHOLD = 20;

    private final ZoomableDraweeView mDraweeView;
    private final PointF mDoubleTapViewPoint = new PointF();
    private final PointF mDoubleTapImagePoint = new PointF();
    private float mDoubleTapScale = 1;
    private boolean mDoubleTapScroll = false;


    public DoubleTapGestureListener(ZoomableDraweeView zoomableDraweeView) {
        mDraweeView = zoomableDraweeView;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        AbstractAnimatedZoomableController zc =
                (AbstractAnimatedZoomableController) mDraweeView.getZoomableController();
        PointF vp = new PointF(e.getX(), e.getY());
        PointF ip = zc.mapViewToImage(vp);
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDoubleTapViewPoint.set(vp);
                mDoubleTapImagePoint.set(ip);
                mDoubleTapScale = zc.getScaleFactor();
                break;
            case MotionEvent.ACTION_MOVE:
                mDoubleTapScroll = mDoubleTapScroll || shouldStartDoubleTapScroll(vp);
                if (mDoubleTapScroll) {
                    float scale = calcScale(vp);
                    zc.zoomToPoint(scale, mDoubleTapImagePoint, mDoubleTapViewPoint);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mDoubleTapScroll) {
                    float scale = calcScale(vp);
                    zc.zoomToPoint(scale, mDoubleTapImagePoint, mDoubleTapViewPoint);
                } else {
                    final float maxScale = zc.getMaxScaleFactor();
                    final float minScale = zc.getMinScaleFactor();
                    if (zc.getScaleFactor() < (maxScale + minScale) / 2) {
                        zc.zoomToPoint(
                                maxScale, ip, vp, DefaultZoomableController.LIMIT_ALL, DURATION_MS, null);
                    } else {
                        zc.zoomToPoint(
                                minScale, ip, vp, DefaultZoomableController.LIMIT_ALL, DURATION_MS, null);
                    }
                }
                mDoubleTapScroll = false;
                break;
            default:
                break;
        }
        return true;
    }


    private boolean shouldStartDoubleTapScroll(PointF viewPoint) {
        double dist =
                Math.hypot(viewPoint.x - mDoubleTapViewPoint.x, viewPoint.y - mDoubleTapViewPoint.y);
        return dist > DOUBLE_TAP_SCROLL_THRESHOLD;
    }

    private float calcScale(PointF currentViewPoint) {
        float dy = (currentViewPoint.y - mDoubleTapViewPoint.y);
        float t = 1 + Math.abs(dy) * 0.001f;
        return (dy < 0) ? mDoubleTapScale / t : mDoubleTapScale * t;
    }

    /**
     * add ziwen
     * 添加实现点击事件
     */
    private OnSingleClickListener mListener;

    /**
     * add ziwen
     * 添加实现点击事件
     * 重写这个方法实现单击监听
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.e("DoubleTapGesture", "onSingleTapConfirmed");
        if (mListener != null) {
            mListener.onSingleClick();
        }
        return true;
    }

    /**
     * add ziwen
     * 点击监听
     */
    public void setOnSingleClick(OnSingleClickListener listener) {
        this.mListener = listener;
    }

    /**
     * add ziwen
     * 实现单击接口
     */
    public interface OnSingleClickListener {
        void onSingleClick();
    }
}
