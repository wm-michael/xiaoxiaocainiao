package im.wangyan.module_ui_demo.swipe_refresh_layout

import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivitySwipeRefreshLayoutBinding

class SwipeRefreshLayoutActivity : BaseActivity<ActivitySwipeRefreshLayoutBinding>() {

    override fun getViewBinding(): ActivitySwipeRefreshLayoutBinding {
        return ActivitySwipeRefreshLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.swipe_refresh_layout)
    }


    override fun initView() {
        mBinding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light
        )

        mBinding.swipeRefreshLayout.setOnRefreshListener {
            mBinding.swipeRefreshLayout.postDelayed({
                //关闭刷新
                mBinding.swipeRefreshLayout.isRefreshing = false
                mBinding.tvRefresh.text = "刷新完成"
            }, 2000)
        }
    }

}