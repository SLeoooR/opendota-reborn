package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pub

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.databinding.HeroesTurboListItemBinding
import com.squareup.picasso.Picasso

class PubHeroesListAdapter(
    private val heroesStats: List<HeroStats>,
    private val totalPicksPerRank: List<Int>,
    private val category: String,
) : RecyclerView.Adapter<PubHeroesListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HeroesTurboListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val heroesTurboListItemBinding = HeroesTurboListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(heroesTurboListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (this.heroesStats.isNotEmpty()) {
            val heroStats: HeroStats = heroesStats[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            val circularProgressDrawable = CircularProgressDrawable(viewHolder.itemView.context).apply {
                strokeWidth = 5F
                centerRadius = 15F
                setColorSchemeColors(ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
                start()
            }

            Picasso.get().load("https://steamcdn-a.akamaihd.net${heroStats.img}").error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(viewHolder.binding.ivHeroIcon)
            viewHolder.binding.tvHeroName.text = heroStats.localizedName

            var wins = 0
            var picks = 0
            var totalPicks = 0
            when (category) {
                "all" -> {
                    wins = heroStats.overallWins
                    picks = heroStats.overallPicks
                    totalPicks = totalPicksPerRank[0]
                }
                "herald" -> {
                    wins = heroStats.heraldWin
                    picks = heroStats.heraldPick
                    totalPicks = totalPicksPerRank[1]
                }
                "guardian" -> {
                    wins = heroStats.guardianWin
                    picks = heroStats.guardianPick
                    totalPicks = totalPicksPerRank[2]
                }
                "crusader" -> {
                    wins = heroStats.crusaderWin
                    picks = heroStats.crusaderPick
                    totalPicks = totalPicksPerRank[3]
                }
                "archon" -> {
                    wins = heroStats.archonWin
                    picks = heroStats.archonPick
                    totalPicks = totalPicksPerRank[4]
                }
                "legend" -> {
                    wins = heroStats.legendWin
                    picks = heroStats.legendPick
                    totalPicks = totalPicksPerRank[5]
                }
                "ancient" -> {
                    wins = heroStats.ancientWin
                    picks = heroStats.ancientPick
                    totalPicks = totalPicksPerRank[6]
                }
                "divine" -> {
                    wins = heroStats.divineWin
                    picks = heroStats.divinePick
                    totalPicks = totalPicksPerRank[7]
                }
                "immortal" -> {
                    wins = heroStats.immortalWin
                    picks = heroStats.immortalPick
                    totalPicks = totalPicksPerRank[8]
                }
            }

            viewHolder.binding.tvHeroPickRate.text = if (picks != 0) {
                String.format("%.2f", ((picks.toFloat() / (totalPicks / 10).toFloat()) * 100.0F)) + "%"
            } else {
                "0%"
            }
            viewHolder.binding.tvHeroWinRate.text = if (wins != 0) {
                String.format("%.2f", ((wins.toFloat() / picks) * 100.0F)) + "%"
            } else {
                "0%"
            }
        }
    }

    override fun getItemCount(): Int = heroesStats.size
}