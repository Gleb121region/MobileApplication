package ru.spbstu.mobileapplication.domain.enums.interview

import android.content.Context
import ru.spbstu.mobileapplication.R

enum class City(private val cityNameResId: Int) {
    MOSCOW(R.string.moscow),
    SAINT_PETERSBURG(R.string.saint_petersburg),
    NOVOSIBIRSK(R.string.novosibirsk),
    YEKATERINBURG(R.string.yekaterinburg),
    KAZAN(R.string.kazan),
    NIZHNY_NOVGOROD(R.string.nizhny_novgorod),
    CHELYABINSK(R.string.chelyabinsk),
    SAMARA(R.string.samara),
    UFA(R.string.ufa),
    ROSTOV_ON_DON(R.string.rostov_on_don),
    OMSK(R.string.omsk),
    KRASNOYARSK(R.string.krasnoyarsk),
    VORONEZH(R.string.voronezh),
    PERM(R.string.perm),
    VOLGOGRAD(R.string.volgograd);

    fun getCityName(context: Context): String {
        return context.getString(cityNameResId)
    }
}
