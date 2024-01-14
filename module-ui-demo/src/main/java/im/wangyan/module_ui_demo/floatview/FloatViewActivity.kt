package im.wangyan.module_ui_demo.floatview

import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityFloatViewBinding

class FloatViewActivity : BaseActivity<ActivityFloatViewBinding>() {
    override fun getViewBinding(): ActivityFloatViewBinding {
        return ActivityFloatViewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.float_view)
    }

    private var mFloatView: AvatarFloatView? = null

    override fun initView() {
        mBinding.btnShowFloat.setOnClickListener {
            mFloatView = AvatarFloatView(this)
            mFloatView?.setDragDistance(0.3)
            if (mBinding.radioTopBottom.isChecked) {
                mFloatView?.setAdsorbType(BaseFloatView.ADSORB_VERTICAL)
            } else {
                mFloatView?.setAdsorbType(BaseFloatView.ADSORB_HORIZONTAL)
            }
            FloatManager.with(this).add(mFloatView!!).show()

//            FloatManager.with(this).add(AvatarFloatView(this))
//                .setClick(object : BaseFloatView.OnFloatClickListener {
//                    override fun onClick(view: View) {
//                        Toast.makeText(this@FloatViewActivity, "click", Toast.LENGTH_SHORT).show()
//                    }
//                })
//                .show()
        }
        mBinding.btnHideFloat.setOnClickListener {
            FloatManager.hide()
        }

        mBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_left_right) {
                mFloatView?.setAdsorbType(BaseFloatView.ADSORB_HORIZONTAL)
            } else {
                mFloatView?.setAdsorbType(BaseFloatView.ADSORB_VERTICAL)
            }
        }
    }

}