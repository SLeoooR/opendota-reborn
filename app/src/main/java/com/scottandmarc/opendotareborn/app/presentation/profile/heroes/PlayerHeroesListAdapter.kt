package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.app.data.hero.info.HeroInfoRepository
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.databinding.PlayerHeroListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.Companion.numTimeAgo
import com.squareup.picasso.Picasso

class PlayerHeroesListAdapter(
    private val playerHeroesList: List<PlayerHero>,
    private val fromOverview: Boolean
) : RecyclerView.Adapter<PlayerHeroesListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PlayerHeroListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val playerHeroListItemBinding = PlayerHeroListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        return ViewHolder(playerHeroListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val heroInfoRepository = DependencyInjector.provideHeroInfoRepository(viewHolder.binding.root.context)
        if (this.playerHeroesList.isNotEmpty()) {
            val playerHero: PlayerHero = playerHeroesList[position]

            val heroInfo = heroInfoRepository.getHeroInfoWhere(playerHero.heroId.toInt())
            val heroWinRate = if (playerHero.games != 0) {
                (playerHero.win.toFloat() / playerHero.games.toFloat()) * 100.0F
            } else {
                0.0F
            }

            val heroPicURL = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png"
            Picasso.get().load(heroPicURL).into(viewHolder.binding.ivPLayerHeroIcon)
            viewHolder.binding.tvPlayerHeroName.text = heroInfo.localizedName
            viewHolder.binding.tvPlayerHeroMP.text = playerHero.games.toString()

            val heroWinRateString = String.format("%.2f", heroWinRate) + "%"
            viewHolder.binding.tvPlayerHeroWR.text = heroWinRateString
            viewHolder.binding.tvPlayerHeroLP.text = numTimeAgo(playerHero.lastPlayed)
        }
    }

    override fun getItemCount(): Int {
        return if (fromOverview) {
            3
        } else {
            20
        }
    }
}