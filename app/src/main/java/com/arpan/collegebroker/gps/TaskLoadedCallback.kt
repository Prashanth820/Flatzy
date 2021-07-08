package com.arpan.collegebroker.gps


interface TaskLoadedCallback {
    fun onTaskDone(vararg values: Any?)
}