package ru.spbstu.mobileapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.spbstu.mobileapplication.data.database.announcement.AnnouncementDbModel
import ru.spbstu.mobileapplication.data.database.announcement.AnnouncementInfoDao
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerInfoDao

@Database(
    entities = [
        AnswerDbModel::class,
        AnnouncementDbModel::class
    ],
    version = 6,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun AnswerInfoDao(): AnswerInfoDao
    abstract fun AnnouncementInfoDao(): AnnouncementInfoDao
}