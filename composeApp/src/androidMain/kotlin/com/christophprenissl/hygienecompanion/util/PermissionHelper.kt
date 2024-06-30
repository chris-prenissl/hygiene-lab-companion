package com.christophprenissl.hygienecompanion.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun requestWritePermission(context: Context) {
    ActivityCompat.requestPermissions(
        context as Activity,
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
        REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE,
    )
}

fun writePermissionApproved(context: Context): Boolean {
    return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )
}
