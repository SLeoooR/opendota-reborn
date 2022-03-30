package com.scottandmarc.opendotareborn.app.data.hero.info

import android.util.Log
import com.scottandmarc.opendotareborn.app.domain.entities.HeroInfo
import com.scottandmarc.opendotareborn.app.domain.gateways.HeroInfoGateway

class HeroInfoRepository(
    private val heroInfoDao: HeroInfoDao,
    private val heroInfoService: HeroInfoEndpoints
) : HeroInfoGateway {
    override suspend fun fetchHeroesInfo(): List<HeroInfo> {
        return try {
            val fetchedHeroesInfo = heroInfoService.fetchHeroesInfo().body()!!

            fun RemoteHeroInfo.toDomain(): HeroInfo {
                return HeroInfo(
                    this.id,
                    this.name,
                    this.localizedName,
                    this.primaryAttr,
                    this.attackType,
                    this.roles
                )
            }

            fetchedHeroesInfo.map {
                it.toDomain()
            }
        } catch (e: Exception) {
            Log.d("error", e.localizedMessage?: "")
            throw e
        }
    }

    override fun insertHeroInfo(heroInfo: HeroInfo) {
        heroInfoDao.insertHeroInfo(
            LocalHeroInfo(
                heroInfo.id,
                heroInfo.name,
                heroInfo.localizedName,
                heroInfo.primaryAttr,
                heroInfo.attackType,
                heroInfo.roles
            )
        )
    }

    override fun deleteHeroesInfo() {
        heroInfoDao.deleteHeroesInfo()
    }

    override fun getHeroesInfo(): List<HeroInfo> {
        val heroesInfo = heroInfoDao.getHeroesInfo()

        fun LocalHeroInfo.toDomain(): HeroInfo {
            return HeroInfo(
                this.id,
                this.name,
                this.localizedName,
                this.primaryAttr,
                this.attackType,
                this.roles
            )
        }

        return heroesInfo.map {
            it.toDomain()
        }
    }

    override fun countHeroesInfo(): Int {
        return heroInfoDao.countHeroesInfo()
    }

    override fun getHeroInfoWhere(id: Int): HeroInfo {
        val heroInfo = heroInfoDao.getHeroesInfoWhere(id)

        return HeroInfo(
            heroInfo.id,
            heroInfo.name,
            heroInfo.localizedName,
            heroInfo.primaryAttr,
            heroInfo.attackType,
            heroInfo.roles,
        )
    }
}