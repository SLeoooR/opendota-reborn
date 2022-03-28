package com.scottandmarc.opendotareborn.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scottandmarc.opendotareborn.app.data.player.LocalPlayer
import com.scottandmarc.opendotareborn.app.data.player.LocalWinLose
import com.scottandmarc.opendotareborn.app.data.player.PlayerDao
import com.scottandmarc.opendotareborn.app.data.player.WinLoseDao

@Database(
    version = 6,
    entities = [LocalPlayer::class, LocalWinLose::class]
)
abstract class OpenDotaRebornDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: OpenDotaRebornDatabase? = null

        fun getDatabase(context: Context): OpenDotaRebornDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OpenDotaRebornDatabase::class.java,
                    "open_dota_reborn_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    //DAOs
    abstract fun getPlayerDao(): PlayerDao
    abstract fun getWinLoseDao(): WinLoseDao
}