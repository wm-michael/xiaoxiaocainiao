package im.wangyan.module_ui_demo.layout_divider

import android.view.View
import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityDividerBinding

class DividerActivity : BaseActivity<ActivityDividerBinding>() {

    private var isShow = true

    override fun getViewBinding(): ActivityDividerBinding {
        return ActivityDividerBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.divider)
    }

    override fun initView() {
        // for android:animateLayoutChanges="true"
        mBinding.btnVisible.setOnClickListener {
            if (isShow) {
                mBinding.tvAbout.visibility = View.GONE
            } else {
                mBinding.tvAbout.visibility = View.VISIBLE
            }
            isShow = !isShow
        }
    }

}