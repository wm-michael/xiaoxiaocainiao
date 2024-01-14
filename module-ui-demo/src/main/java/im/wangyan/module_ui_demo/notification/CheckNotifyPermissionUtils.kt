package im.wangyan.module_ui_demo.notification

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat

class CheckNotifyPermissionUtils {
    /**
     * 系统层面通知开关有没有开启
     * Build.VERSION.SDK_INT >= 24
     * Build.VERSION.SDK_INT >= 19
     *
     * @param mContext
     * @return
     */
    fun checkNotifyPermission(mContext: Context): Boolean {
        val manager = NotificationManagerCompat.from(mContext)
        return manager.areNotificationsEnabled()
    }


    /**
     * 如果通知未打开 跳转到通知设定界面
     * @param mContext
     */
    fun tryJumpNotifyPage(mContext: Context) {
        val intent = Intent()
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, mContext.packageName)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS")
                intent.putExtra("app_package", mContext.packageName)
                intent.putExtra("app_uid", mContext.applicationInfo.uid)
            } else {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.setData(Uri.parse("package:" + mContext.packageName))
            }
            mContext.startActivity(intent)
        } catch (e: Exception) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", mContext.packageName, null)
            intent.setData(uri)
            mContext.startActivity(intent)
        }
    }
}