package im.wangyan.module_ui_demo.dragrecyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(private val spanCount: Int, private val spacing: Int = 20, private var includeEdge: Boolean = false) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        recyclerView.layoutManager?.let {
            when (recyclerView.layoutManager) {
                is GridLayoutManager -> {
                    val position = recyclerView.getChildAdapterPosition(view) // 获取item在adapter中的位置
                    val column = position % spanCount // item所在的列
                    if (includeEdge) {
                        outRect.left = spacing - column * spacing / spanCount
                        outRect.right = (column + 1) * spacing / spanCount
                        if (position < spanCount) {
                            outRect.top = spacing
                        }
                        outRect.bottom = spacing
                    } else {
                        outRect.left = column * spacing / spanCount
                        outRect.right = spacing - (column + 1) * spacing / spanCount
                        if (position >= spanCount) {
                            outRect.top = spanCount
                        }
                        outRect.bottom = spacing
                    }
                }
                is LinearLayoutManager -> {
                    outRect.top = spanCount
                    outRect.bottom = spacing
                }
            }
        }
    }

}