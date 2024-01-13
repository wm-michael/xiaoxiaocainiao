package im.wangyan.module_ui_demo.cardview

import android.widget.Toast
import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityCardViewBinding

class CardViewActivity : BaseActivity<ActivityCardViewBinding>() {
    override fun getViewBinding(): ActivityCardViewBinding {
        return ActivityCardViewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.card_view)
    }

    override fun initView() {
        mBinding.cardViewElevated.setOnClickListener {
            Toast.makeText(this@CardViewActivity, "CardView", Toast.LENGTH_SHORT).show()
        }
    }

}