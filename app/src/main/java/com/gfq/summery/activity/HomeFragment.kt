package com.gfq.summery.activity

import androidx.core.view.get
import com.gfq.common.base.BaseFragment
import com.gfq.common.helper.load.LoadingView
import com.gfq.common.view.setDebounceClick
import com.gfq.summery.R
import com.gfq.summery.databinding.FragmentHomeBinding

/**
 *  2022/11/4 15:20
 * @auth gaofuq
 * @description
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initViews() {
        fragBinding.tvConfirm.setDebounceClick {
            LoadingView.withActivity()?.show("xxxx")?.appendShow("-yyyy")?.dismissDelay(2000)
        }
    }
}