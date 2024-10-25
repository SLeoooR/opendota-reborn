package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.databinding.PlayerHeroListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.numTimeAgo
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

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            val heroInfo = heroInfoRepository.getHeroInfoWhere(playerHero.heroId.toInt())
            val heroWinRate = if (playerHero.games != 0) {
                (playerHero.win.toFloat() / playerHero.games.toFloat()) * 100.0F
            } else {
                0.0F
            }

            val circularProgressDrawable = CircularProgressDrawable(viewHolder.itemView.context).apply {
                strokeWidth = 5F
                centerRadius = 15F
                setColorSchemeColors(ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
                start()
            }

            val heroPicURL = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png"
            Picasso.get().load(heroPicURL).error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(viewHolder.binding.ivPLayerHeroIcon)
            viewHolder.binding.tvPlayerHeroName.text = heroInfo.localizedName
            viewHolder.binding.tvPlayerHeroMP.text = playerHero.games.toString()

            val heroWinRateString = String.format("%.2f", heroWinRate) + "%"
            viewHolder.binding.tvPlayerHeroWR.text = heroWinRateString

            if (playerHero.games == 0) {
                viewHolder.binding.tvPlayerHeroLP.text = "N/A"
            } else {
                viewHolder.binding.tvPlayerHeroLP.text = numTimeAgo(playerHero.lastPlayed)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (fromOverview) {
            3
        } else {
            playerHeroesList.size
        }
    }
}