package im.wangyan.module_ui_demo.guideline

import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityGuideLineBinding

class GuideLineActivity : BaseActivity<ActivityGuideLineBinding>() {
    override fun getViewBinding(): ActivityGuideLineBinding {
        return ActivityGuideLineBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.guide_line)
    }

    override fun initView() {
    }

}