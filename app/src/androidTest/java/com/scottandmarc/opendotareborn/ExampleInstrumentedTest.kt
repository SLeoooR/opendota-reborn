package com.scottandmarc.opendotareborn


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.scottandmarc.opendotareborn.app.data.OpenDotaRebornDatabase
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoDao
import com.scottandmarc.opendotareborn.app.data.hero.info.LocalHeroInfo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var db: OpenDotaRebornDatabase
    lateinit var heroInfoDao: HeroInfoDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            OpenDotaRebornDatabase::class.java
        ).allowMainThreadQueries().build()

        heroInfoDao = db.getHeroInfoDao()
    }

    @After
    fun clean() {
        db.close()
    }

    @Test
    fun testInsertHero() {
//     clea
        val itemCount = heroInfoDao.getHeroesInfo().size

        assertThat(itemCount == 1).isTrue()
    }

}