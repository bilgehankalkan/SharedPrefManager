package com.ashokvarma.sharedprefmanager

import android.content.Context
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
        //no-op
    }
}