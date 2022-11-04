package com.gfq.summery.activity

//import com.google.android.exoplayer2.ExoPlayer
import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.*
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gfq.baservadapter.refresh.*
import com.gfq.common.base.BaseActivity
import com.gfq.common.helper.actlifecycle.doOnCreated
import com.gfq.common.helper.actlifecycle.doOnResumed
import com.gfq.common.helper.actlifecycle.doOnStarted
import com.gfq.common.system.loge
import com.gfq.common.utils.dpF
import com.gfq.common.utils.setImmerseAndDarkFont
import com.gfq.summery.R
import com.gfq.summery.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    val imgUrl =
        "http://mdwl-miyun.oss-cn-beijing.aliyuncs.com/image/0c1ec4f6f61140da8aa87ff559bc4db8.gif"


    private val homePage by lazy { HomeFragment() }
    private val myPage by lazy { MyFragment() }

    override fun initView() {
        setImmerseAndDarkFont()

        actBinding.pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int): Fragment {
               return when(position){
                    0 -> homePage
                    1 -> myPage
                    else -> homePage
                }
            }
        }
        //禁止左右滑动
        actBinding.pager.isUserInputEnabled = false
        //去掉自带的颜色效果
        actBinding.navView.itemIconTintList = null
        //去掉水波纹效果
        actBinding.navView.itemRippleColor = ColorStateList.valueOf(Color.TRANSPARENT)

        // actBinding.navView.menu.clear()
        // actBinding.navView.inflateMenu(R.menu.bottom_nav_menu)
        actBinding.navView.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.navHome ->{
                        actBinding.pager.currentItem = 0
                    }
                    R.id.navMe ->{
                        actBinding.pager.currentItem = 1
                    }
                }
                return true
            }
        })


        doOnResumed { loge("doOnResumed") }
    }

    override fun onResume() {
        loge("doOnResumed before")
        super.onResume()
        loge("doOnResumed late")
    }

}






