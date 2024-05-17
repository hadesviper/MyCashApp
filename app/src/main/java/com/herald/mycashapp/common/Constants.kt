package com.herald.mycashapp.common

import android.content.Context
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.herald.mycashapp.R

object Constants {
    const val BASE_URL = "https://yogahez.mountasher.online/api/"
    const val BEARER_AUTH = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiM2NjMGI5YTU0NTc0Zjc1NjYzNmE0ZDdlYTI5NmJmNWQ1NTdhZmU0MzljZDhjNmE1ODA3Mzk1MTU4YjU2MmIwYjc4YzU0ZTNlOGYxNDZjM2UiLCJpYXQiOjE2MzE3MDA1MzgsIm5iZiI6MTYzMTcwMDUzOCwiZXhwIjoxNjYzMjM2NTM4LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.sBbbYmRW95gxfOyHHadgGRDlh_j8Zk0OBBEW0jBUgZG1zzNDjnfXO1_13yPvT6TSBgg0Py95wpwN7zDqMfI-1myuMJ2zqa6g9AqAEWqDwrfrP-XZpV0HXoycRknFkKCt3rzVPs05DT864_0fSItpN8MhLsu-P0jbzYSrEWe0OCFglq8Sw2B2N0-wHbNNoreEBElvC5nmepnWgVdxuKLi7YcU2VjjT8qOWLuTMbIXjPovXUFhQl6JVry1AOuPsiXRf5Dtm0d99D8smFmIi7Op9bh6a9iY61EAOGeOz9uUtNJ8enZa92qFGVmUyMDKcO0zGpyUDSOzVGxnaebfdDipeCCCNnGYtFNgPHv9CWfkYIyNNlAy01ukVOxF3ULZ-BLE2YrJfcu7HwSVHdC_LU0ACZa29K6kcJU-QJ0Vr5afArqRnB_k-hYWQTcT4FQMIB9XsvnZiFSsaJ__7NHvW0AK6mWKVVkepp1X0Nhfl0-IiPDtbHZ0o_Poa0SsbK_Z7m1bWMq4i9fW_aQ6lJVeekCivIn3EcPltH2EF5HEuhjSqqsk1bq8025DOI4axoRv-kRp9I2Rl30WbgcH0sKCUbZhtpWnwiQ9-dbh89k6zLIgp7v6bJgM23W4aemnge1WpEsminyjp3w9f0j7fNT6Ba_ec_LGuNAdVkJoh_AOnb2mK9Q"
    const val MESSAGE_FORM_INCOMPLETE = "Please Re-check the form"
    const val DEVICE_TOKEN = ""


    fun showErrorDialog( context: Context,message: String) {
        AlertDialog.Builder(context)
            .setMessage("Error message: $message")
            .setTitle("An error has occurred")
            .setNegativeButton("Dismiss!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
            }
    }

    fun statusBarVisibility(visible: Boolean,activity: FragmentActivity){
        if (visible){
            showStatusBar(activity)
        }
        else
        {
            hideStatusBar(activity)
        }
    }

    private fun showStatusBar(activity: FragmentActivity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            activity.window.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
    private fun hideStatusBar(activity: FragmentActivity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}