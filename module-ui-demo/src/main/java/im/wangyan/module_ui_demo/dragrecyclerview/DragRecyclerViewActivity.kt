package im.wangyan.module_ui_demo.dragrecyclerview

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityDragRecyclerViewBinding

class DragRecyclerViewActivity : BaseActivity<ActivityDragRecyclerViewBinding>() {

    private val tag = "XIAOXIAO"

    companion object {
        private var SPAN_COUNT = 4
    }

    private lateinit var mAdapter: DragAdapter
    private lateinit var mGridItemDecoration: GridSpaceItemDecoration

    override fun getViewBinding(): ActivityDragRecyclerViewBinding {
        return ActivityDragRecyclerViewBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.drag_recyclerview)
    }

    override fun initView() {
        initRecycleView()
        setListener()
    }

    private fun initRecycleView() {
        mBinding.recycleView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        if (!::mGridItemDecoration.isInitialized) {
            mGridItemDecoration = GridSpaceItemDecoration(SPAN_COUNT)
            mBinding.recycleView.addItemDecoration(mGridItemDecoration, 0)
        }
        val list = getDatas()
        mAdapter = DragAdapter(this, list)
        mBinding.recycleView.adapter = mAdapter

        // 设置拖拽/滑动
        val dragCallBack = DragCallBack(mAdapter, list)
        val itemTouchHelper = ItemTouchHelper(dragCallBack)
        itemTouchHelper.attachToRecyclerView(mBinding.recycleView)

        mAdapter.setOnItemClickListener(object : DragAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@DragRecyclerViewActivity, dragCallBack.getData()[position], Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(holder: DragAdapter.ViewHolder) {
                if (holder.adapterPosition != mAdapter.fixedPosition) {
                    itemTouchHelper.startDrag(holder)
                }
            }
        })
    }

    private fun setListener() {
        mBinding.tvSwitch.setOnClickListener {
            when (mBinding.recycleView.layoutManager) {
                is GridLayoutManager -> {
                    mBinding.recycleView.layoutManager = LinearLayoutManager(this)
                }
                else -> {
                    mBinding.recycleView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
                }
            }
        }

        mAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                Log.i(tag, "onItemRangeMoved")
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                Log.i(tag, "onItemRangeRemoved")
            }

            override fun onChanged() {
                super.onChanged()
                Log.i(tag, "onChanged")
            }
        })
    }

    private fun getDatas(): MutableList<String> {
        return mutableListOf(
            "推荐",
            "Android",
            "iOS",
            "前端",
            "后端",
            "音视频",
            "大数据",
            "人工智能",
            "云原生",
            "运维",
            "算法",
            "代码人生"
        )
    }

}