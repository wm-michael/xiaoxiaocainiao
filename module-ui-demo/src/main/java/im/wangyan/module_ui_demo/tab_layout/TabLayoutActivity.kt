package im.wangyan.module_ui_demo.tab_layout

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityTabLayoutBinding
import im.wangyan.module_ui_demo.tab_layout.fragments.Fragment1
import im.wangyan.module_ui_demo.tab_layout.fragments.Fragment2
import im.wangyan.module_ui_demo.tab_layout.fragments.Fragment3

class TabLayoutActivity : BaseActivity<ActivityTabLayoutBinding>() {

    private var defaultIndex = 0
    private val tabTitles = arrayOf("Android", "Kotlin", "Flutter")
    private val tabIcons = arrayOf(
        R.drawable.baseline_brunch_dining_24,
        R.drawable.baseline_bungalow_24,
        R.drawable.baseline_cruelty_free_24
    )

    override fun getViewBinding(): ActivityTabLayoutBinding {
        return ActivityTabLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.tab_layout)
    }

    override fun initView() {
        initViewPager()

        initTabLayout1()//基础样式
        initTabLayout2()//添加icon & 去掉indicator
//        initTabLayout3()//style 字体大小、加粗 & 顶部indicator
//        initTabLayout4()//下划线样式 & tab分割线
//        initTabLayout5()//Badge 数字 & 红点
//        initTabLayout6()//TabLayout样式 & 选中字体加粗
//        initTabLayout7()//获取隐藏tab
//        initTabLayout8()//自定义item view & lottie
    }

    /**
     * 基础样式
     */
    private fun initTabLayout1() {
        //tabLayout关联viewpager
        TabLayoutMediator(mBinding.tabLayout1, mBinding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
        //设置默认选中
        mBinding.tabLayout1.getTabAt(defaultIndex)?.select()
    }

    /**
     * 添加icon & 去掉indicator
     */
    private fun initTabLayout2() {
        TabLayoutMediator(mBinding.tabLayout2, mBinding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
            tab.icon = ContextCompat.getDrawable(this, tabIcons[position])
        }.attach()
        //设置默认选中
        mBinding.tabLayout2.getTabAt(defaultIndex)?.select()
    }

    private fun initViewPager() {
        //viewpager设置adapter
        mBinding.viewPager.adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.tabLayout8.getTabAt(position)?.select()
            }
        })
        Log.d("TAG", "initViewPager: " + mBinding.viewPager.childCount)
    }

    private inner class SimpleFragmentPagerAdapter constructor(fm: FragmentManager) :
    /*
    在使用FragmentPagerAdapter或FragmentStatePagerAdapter时使用BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT标识，
    之后Fragment在可见时都会调用onResume()方法，不可见时会调用onPause()方法，因此在onResume()中拉取数据就可以实现懒加载了。
     */
        FragmentStateAdapter(fm, lifecycle) {
        private val fragment = arrayOf(Fragment1(), Fragment2(), Fragment3())

        override fun getItemCount(): Int {
            return fragment.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragment[position]
        }
    }
}