package im.wangyan.module_ui_demo.collapsing_toolbar

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import im.wangyan.module_ui_demo.databinding.ActivityTabViewPagerScrollBinding
import im.wangyan.module_ui_demo.fragments.Fragment2
import im.wangyan.module_ui_demo.fragments.Fragment4

class TabViewPagerScrollActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabViewPagerScrollBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabViewPagerScrollBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //透明状态栏
        val decorView: View = window.decorView
        val option: Int = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        decorView.systemUiVisibility = option
        window.statusBarColor = Color.TRANSPARENT

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        binding.toolbarLayout.title = "TabViewPagerScroll"
        binding.viewPager.adapter = SimpleFragmentPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    inner class SimpleFragmentPagerAdapter constructor(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val tabTitles = arrayOf("菜单一", "菜单二")
        private val mFragment = arrayOf(Fragment4(), Fragment2())

        override fun getItem(position: Int): Fragment {
            return mFragment[position]
        }

        override fun getCount(): Int {
            return mFragment.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }
}