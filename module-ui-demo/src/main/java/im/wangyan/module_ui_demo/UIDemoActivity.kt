package im.wangyan.module_ui_demo

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import im.wangyan.module_ui_demo.bottom_navigation.BottomNavigationActivity
import im.wangyan.module_ui_demo.bottomsheet.BottomSheetActivity
import im.wangyan.module_ui_demo.cardview.CardViewActivity
import im.wangyan.module_ui_demo.collapsing_toolbar.CollapsingToolbarActivity
import im.wangyan.module_ui_demo.databinding.ActivityUidemoBinding
import im.wangyan.module_ui_demo.drawer_layout.DrawerLayoutActivity
import im.wangyan.module_ui_demo.searchview.SearchViewActivity
import im.wangyan.module_ui_demo.snackbar.SnackbarActivity
import im.wangyan.module_ui_demo.swipe_refresh_layout.SwipeRefreshLayoutActivity
import im.wangyan.module_ui_demo.tab_layout.TabLayoutActivity
import im.wangyan.module_ui_demo.tablayout_customview.TabLayoutCustomViewActivity
import im.wangyan.module_ui_demo.text_input_layout.TextInputLayoutActivity

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
                when(position) {
                    0 -> openActivity(SwipeRefreshLayoutActivity::class.java)
                    1 -> openActivity(SnackbarActivity::class.java)
                    2 -> openActivity(TabLayoutActivity::class.java)
                    3 -> openActivity(CardViewActivity::class.java)
                    4 -> openActivity(BottomNavigationActivity::class.java)
                    5 -> openActivity(CollapsingToolbarActivity::class.java)
                    6 -> openActivity(TextInputLayoutActivity::class.java)
                    7 -> openActivity(SearchViewActivity::class.java)
                    8 -> openActivity(TabLayoutCustomViewActivity::class.java)
                    9 -> openActivity(DrawerLayoutActivity::class.java)
                    10 -> openActivity(BottomSheetActivity::class.java)
                }
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