package com.scottandmarc.opendotareborn.app.presentation.profile.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.databinding.MatchListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.Companion.numTimeAgo
import com.squareup.picasso.Picasso

class MatchesListAdapter(
    private val matchesList: List<Match>,
    private val fromOverview: Boolean
) : RecyclerView.Adapter<MatchesListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MatchListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val matchListItemBinding = MatchListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(matchListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val heroInfoRepository = DependencyInjector.provideHeroInfoRepository(viewHolder.binding.root.context)
        if (this.matchesList.isNotEmpty()) {
            val match: Match = matchesList[position]

            val heroInfo = heroInfoRepository.getHeroInfoWhere(match.heroId)
            val heroPicURL = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png"
            Picasso.get().load(heroPicURL).into(viewHolder.binding.ivPLayerHeroIcon)

            val playerSide = when (match.playerSlot) {
                in 0..127 -> {
                    "radiant"
                }
                in 128..255 -> {
                    "dire"
                }
                else -> {
                    ""
                }
            }

            var wlString = ""
            if (playerSide == "radiant" && match.radiantWin) {
                wlString = "Win"
                viewHolder.binding.tvWinOrLose.text = wlString
                viewHolder.binding.tvWinOrLose.setTextColor(ContextCompat.getColor(viewHolder.binding.root.context, R.color.green))
            } else {
                wlString = "Lose"
                viewHolder.binding.tvWinOrLose.text = wlString
                viewHolder.binding.tvWinOrLose.setTextColor(ContextCompat.getColor(viewHolder.binding.root.context, R.color.red))
            }

            val kdaString = "${match.kills}/${match.deaths}/${match.assists}"
            viewHolder.binding.tvKDA.text = kdaString

            val matchDurationInSeconds = match.duration
            val hour = (matchDurationInSeconds % (24 * 3600)) / 3600
            val minutes = (matchDurationInSeconds % 3600) / 60

            val matchLengthString = "$hour:$minutes"
            viewHolder.binding.tvLength.text = matchLengthString

            viewHolder.binding.tvMatchLP.text = numTimeAgo((match.startTime + match.duration))
        }
    }

    override fun getItemCount(): Int {
        return if (fromOverview) {
            3
        } else {
            matchesList.size
        }
    }
}