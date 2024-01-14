package im.wangyan.module_ui_demo.materialbutton

import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityMaterialButtonBinding

class MaterialButtonActivity : BaseActivity<ActivityMaterialButtonBinding>() {
    override fun getViewBinding(): ActivityMaterialButtonBinding {
        return ActivityMaterialButtonBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.material_button)
    }

    override fun initView() {
    }

}