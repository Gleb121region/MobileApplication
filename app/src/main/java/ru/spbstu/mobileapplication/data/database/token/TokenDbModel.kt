package ru.spbstu.mobileapplication.data.database.token

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.TokenType


@Parcelize
@Entity(tableName = "tokens")
data class TokenDbModel(
    @PrimaryKey(autoGenerate = true) val tokenId: Int = 0,
    val token: String,
    val refreshToken: String,
    val tokenType: TokenType = TokenType.BEARER
) : Parcelable