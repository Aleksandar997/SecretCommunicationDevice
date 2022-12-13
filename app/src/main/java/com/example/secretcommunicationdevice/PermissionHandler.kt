package com.example.secretcommunicationdevice

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

class PermissionHandler(
    private val activity: ComponentActivity
) {
    fun requestPermission(
        permission: String
    ): Boolean {
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        var isGranted = false
        val requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isPermGranted: Boolean ->
            isGranted = isPermGranted
        }
        requestPermissionLauncher.launch(permission)
        return isGranted
    }
}