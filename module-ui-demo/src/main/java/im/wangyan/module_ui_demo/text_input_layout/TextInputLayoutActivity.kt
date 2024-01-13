package im.wangyan.module_ui_demo.text_input_layout

import android.text.Editable
import android.text.TextWatcher
import im.wangyan.module_ui_demo.BaseActivity
import im.wangyan.module_ui_demo.R
import im.wangyan.module_ui_demo.databinding.ActivityTextInputLayoutBinding

class TextInputLayoutActivity : BaseActivity<ActivityTextInputLayoutBinding>() {
    override fun getViewBinding(): ActivityTextInputLayoutBinding {
        return ActivityTextInputLayoutBinding.inflate(layoutInflater)
    }

    override fun setToolbar() {
        mToolbar.setTitle(R.string.text_input_layout)
    }

    override fun initView() {
        mBinding.etName.addTextChangedListener(mTextWatcher)
    }

    private val mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            if (mBinding.tilName.editText!!.text.length > mBinding.tilName.counterMaxLength)
                mBinding.tilName.error = "输入内容超过上限"
            else
                mBinding.tilName.error = null
        }
    }

}