package ru.spbstu.mobileapplication.domain.user.repository

interface IsUserFirstTimeRepository {
    fun changeFirstTimeStatus()
    fun getFirstTimeStatus(): Boolean
}