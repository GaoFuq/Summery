package com.gfq.summery.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.customview.widget.ViewDragHelper

/**
 *  2022/5/23 15:51
 * @auth gaofuq
 * @description
 */

class DragConsLayout(
    context: Context,
    attrs: AttributeSet?,
) : ConstraintLayout(context, attrs) {

    var captureViewOldX = 0f
    var captureViewOldY = 0f

    val callback :ViewDragHelper.Callback= object : ViewDragHelper.Callback(){
        // 确定是否要拖拽
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }
        // clamp 夹钳, 钳制view的横向位置
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }
        // clamp 夹钳, 钳制view的纵向位置
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }
        // 开始拖拽
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            //记录拖拽view的初始位置
            captureViewOldX = capturedChild.x
            captureViewOldY=capturedChild.y
        }

        // 拖拽中
        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int,
        ) {

        }

        //松手，拖拽结束
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            //返回到该view的初始位置
            helper.settleCapturedViewAt(captureViewOldX.toInt(),captureViewOldY.toInt())
            invalidate()
//            postInvalidateOnAnimation()
//            postInvalidate()
        }

    }

    val helper = ViewDragHelper.create(this,callback)


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return helper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
         helper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if(helper.continueSettling(true)){
            invalidate()
//            postInvalidateOnAnimation()
//            postInvalidate()
        }
    }
}