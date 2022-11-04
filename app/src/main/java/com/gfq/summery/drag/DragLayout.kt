package com.gfq.summery.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.NestedScrollingParent3
import androidx.core.widget.NestedScrollView
import androidx.customview.widget.ViewDragHelper

/**
 *  2022/5/23 15:51
 * @auth gaofuq
 * @description
 */

class DragLayout(
    context: Context,
    attrs: AttributeSet?,
) : NestedScrollView(context, attrs) {


    var maxTopOffset = 0
    var maxBottomOffset = 0

    var onDrag:((progress:Float)->Unit)?=null

    val callback :ViewDragHelper.Callback= object : ViewDragHelper.Callback(){
        // 确定是否要拖拽
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }
        // clamp 夹钳, 钳制view的横向位置
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return 0
        }
        // clamp 夹钳, 钳制view的纵向位置
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            if (top <= -maxTopOffset) {
                return -maxTopOffset
            }
            if (top >=  maxBottomOffset) {
                return maxBottomOffset
            }

            return top
        }
        // 开始拖拽
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            //记录拖拽view的初始位置
//            captureViewOldX = capturedChild.x
//            captureViewOldY=capturedChild.y
        }

        // 拖拽中
        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int,
        ) {
            onDrag?.invoke(top.toFloat()/maxBottomOffset)
        }

        //松手，拖拽结束
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            //返回到该view的初始位置
            helper.settleCapturedViewAt(0,0)
            invalidate()
//            postInvalidateOnAnimation()
//            postInvalidate()
        }

    }

    val helper = ViewDragHelper.create(this,callback)


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        return helper.shouldInterceptTouchEvent(ev)
        var y=0f
       when(ev.action){
           MotionEvent.ACTION_DOWN->{
                y = ev.y
           }
           MotionEvent.ACTION_MOVE->{
               if(ev.y - y > 10){
                   return true
               }
           }
       }
        return false
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