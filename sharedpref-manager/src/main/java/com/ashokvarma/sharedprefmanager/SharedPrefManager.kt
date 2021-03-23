package com.ashokvarma.sharedprefmanager

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.ArrayList

/**
 * Class description
 *
 * @author ashokvarma
 * @version 1.0
 * @since 21 Jun 2017
 */
object SharedPrefManager {

    @JvmStatic
    fun launchSharedPrefManager(
        context: Context,
        privateSharedPrefNames: ArrayList<String> = arrayListOf(),
        worldReadSharedPrefNames: ArrayList<String> = arrayListOf(),
        worldWriteSharedPrefNames: ArrayList<String> = arrayListOf()
    ) {
        val worldModeSupported = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
        val worldModeSharedPrefCount = (worldReadSharedPrefNames.size) + (worldWriteSharedPrefNames.size)
        val worldModeSupportedSharedPrefCount = if (worldModeSupported) worldModeSharedPrefCount else 0
        val totalSupportedSharedPrefCount = (privateSharedPrefNames.size) + worldModeSupportedSharedPrefCount
        if (totalSupportedSharedPrefCount == 0) {
            Log.e("SharedPrefManager", "No Supported Shared Pref Names so skipping activity call")
            return
        }

        val intent = Intent(context, SharedPrefManagerActivity::class.java).apply {
            putStringArrayListExtra(SharedPrefManagerActivity.PRIVATE_SHARED_PREF_NAMES, privateSharedPrefNames)
            if (worldModeSupported) {
                putStringArrayListExtra(SharedPrefManagerActivity.WORLD_READ_SHARED_PREF_NAMES, worldReadSharedPrefNames)
                putStringArrayListExtra(SharedPrefManagerActivity.WORLD_WRITE_SHARED_PREF_NAMES, worldWriteSharedPrefNames)
            } else {
                if (worldModeSharedPrefCount != 0) {
                    Log.e(
                        "SharedPrefManager",
                        "MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE are not supported above Android N (Nougat)"
                    )
                }
            }
        }

        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
