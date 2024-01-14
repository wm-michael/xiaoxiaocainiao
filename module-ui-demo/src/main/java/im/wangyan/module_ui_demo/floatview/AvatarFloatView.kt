package im.wangyan.module_ui_demo.floatview

import android.content.Context
import android.view.View
import com.google.android.material.imageview.ShapeableImageView
import im.wangyan.module_ui_demo.R

class AvatarFloatView (context: Context) : BaseFloatView(context) {

    private var mAdsorbType = ADSORB_VERTICAL

    override fun getChildView(): View {
        val imageView = ShapeableImageView(context)
        imageView.setImageResource(R.drawable.ic_avatar)
        return imageView
    }

    override fun getIsCanDrag(): Boolean {
        return true
    }

    override fun getAdsorbType(): Int {
        return mAdsorbType
    }

    fun setAdsorbType(type: Int) {
        mAdsorbType = type
    }
}