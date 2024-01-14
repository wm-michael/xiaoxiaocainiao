package im.wangyan.module_ui_demo.dynamic_layout

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityDynamicLayoutBinding

class DynamicLayoutActivity : BaseActivity<ActivityDynamicLayoutBinding>() {
    override fun getViewBinding(): ActivityDynamicLayoutBinding {
        return ActivityDynamicLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.dynamic_layout)
    }

    override fun initView() {
        setTouchListener()
    }

    private var mDownY = 0F

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener() {
        mBinding.layoutLine.setOnTouchListener { v, event ->
            val y = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mDownY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val topHeight = (y - mDownY).toInt()
                    ViewCompat.offsetTopAndBottom(v, (y - mDownY).toInt())
                    refreshTopLayout(topHeight)
                }
                MotionEvent.ACTION_UP -> {
                    // TODO: 保存当前位置、
                    // TODO: 优化：添加上下布局的最大、最小高度约束
                }
            }
            true
        }
    }

    private fun refreshTopLayout(topHeight: Int) {
        Log.d("aaa topHeight = ", topHeight.toString())
        val layoutParams = mBinding.layoutTop.layoutParams as ViewGroup.LayoutParams
        layoutParams.height = layoutParams.height + topHeight
        mBinding.layoutTop.layoutParams = layoutParams
    }

}