package ru.spbstu.mobileapplication.data.storage.auth

interface IsUserFirstTimeStorage {
    fun changeFirstTimeStatus()
    fun getFirstTimeStatus(): Boolean

    companion object {
        const val FIRST_TIME_STATUS = "firstTimeStatus"
    }
}