package im.wangyan.module_ui_demo

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import im.wangyan.module_ui_demo.databinding.ActivityUidemoBinding

class UIDemoActivity : BaseActivity<ActivityUidemoBinding>() {

    private lateinit var mList: MutableList<String>
    private lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("XIAOXIAO", "onCreate")
    }

    override fun getViewBinding(): ActivityUidemoBinding {
        return ActivityUidemoBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
    }

    override fun initView() {
        mToolbar.navigationIcon = null

        //回调刷新toolbar的menu，页面初始化或者在需要的时候调用
        invalidateOptionsMenu()
        mList = mutableListOf(
            getString(R.string.swipe_refresh_layout),
            getString(R.string.floating_action_button),
            getString(R.string.snack_bar),
            getString(R.string.tab_layout),
            getString(R.string.card_view),
            getString(R.string.bottom_navigation),
            getString(R.string.collapsing_toolbar),
            getString(R.string.text_input_layout),
            getString(R.string.search_view),
            getString(R.string.tab_layout_custom_view),
            getString(R.string.drawer_layout),
            getString(R.string.bottom_sheet),
            getString(R.string.material_button),
            getString(R.string.shapeable_image_view),
            getString(R.string.badge_drawable),
            getString(R.string.drag_recyclerview),
            getString(R.string.notification),
            getString(R.string.float_view),
            getString(R.string.guide_line),
            getString(R.string.divider),
            getString(R.string.dynamic_layout)
        )

        mAdapter = MainAdapter(this, mList)
        mBinding.recycleView.adapter = mAdapter

        setListener()

        initFloatingButtonImage()

    }

    /**
     * 修改主题后会重建，初始化显示icon
     */
    private fun initFloatingButtonImage() {
        if (isDarkTheme()) {
            mBinding.floatingButton.setImageResource(R.drawable.baseline_settings_system_daydream_24)
        } else {
            mBinding.floatingButton.setImageResource(R.drawable.baseline_mode_night_24)
        }
    }



    private fun setListener() {
        mAdapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {

            }
        })

        mBinding.floatingButton.setOnClickListener {
            if (isDarkTheme()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    /**
     * 是否深色主题
     */
    private fun isDarkTheme(): Boolean {
        val flag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }

    private fun openActivity(targetActivityClass: Class<*>) {
        startActivity(Intent(this@UIDemoActivity, targetActivityClass))
    }
}