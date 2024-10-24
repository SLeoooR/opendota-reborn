package com.scottandmarc.opendotareborn.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoDao
import com.scottandmarc.opendotareborn.app.data.hero.info.LocalHeroInfo
import com.scottandmarc.opendotareborn.app.data.player.LocalPlayer
import com.scottandmarc.opendotareborn.app.data.player.PlayerDao
import com.scottandmarc.opendotareborn.toolbox.helpers.Converters

@Database(
    entities = [
        LocalPlayer::class,
        LocalHeroInfo::class,
    ],
    version = 17,
    exportSchema = false
)
@TypeConverters(Converters::class)
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
    abstract fun getHeroInfoDao(): HeroInfoDao
}