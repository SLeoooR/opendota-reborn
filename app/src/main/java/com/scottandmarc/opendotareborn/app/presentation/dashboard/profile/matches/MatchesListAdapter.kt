package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Match
import com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.matches.matchDetails.MatchDetailsActivity
import com.scottandmarc.opendotareborn.databinding.MatchListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.numTimeAgo
import com.squareup.picasso.Picasso

class MatchesListAdapter(
    private val matchesList: List<Match>,
    private val fromOverview: Boolean,
) : RecyclerView.Adapter<MatchesListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MatchListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val mPosition = layoutPosition
            val matchId: Long = matchesList[mPosition].matchId

            val intent = Intent(v.context, MatchDetailsActivity::class.java)
            intent.putExtra("matchId", matchId)
            intent.putExtra("title", "Match Details")
            v.context.startActivity(intent)
        }
    }

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

            val heroInfo = heroInfoRepository.getHeroInfoWhere(match.heroId)
            val heroPicURL = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png"
            Picasso.get().load(heroPicURL).error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(viewHolder.binding.ivPLayerHeroIcon)

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

            val wlString: String
            if (playerSide == "radiant") {
                if (match.radiantWin) {
                    wlString = "Win"
                    viewHolder.binding.tvWinOrLose.text = wlString
                    viewHolder.binding.tvWinOrLose.setTextColor(ContextCompat.getColor(viewHolder.binding.root.context, R.color.green))
                } else {
                    wlString = "Lose"
                    viewHolder.binding.tvWinOrLose.text = wlString
                    viewHolder.binding.tvWinOrLose.setTextColor(ContextCompat.getColor(viewHolder.binding.root.context, R.color.red))
                }
            } else {
                if (!match.radiantWin) {
                    wlString = "Win"
                    viewHolder.binding.tvWinOrLose.text = wlString
                    viewHolder.binding.tvWinOrLose.setTextColor(ContextCompat.getColor(viewHolder.binding.root.context, R.color.green))
                } else {
                    wlString = "Lose"
                    viewHolder.binding.tvWinOrLose.text = wlString
                    viewHolder.binding.tvWinOrLose.setTextColor(ContextCompat.getColor(viewHolder.binding.root.context, R.color.red))
                }
            }

            val kdaString = "${match.kills}/${match.deaths}/${match.assists}"
            viewHolder.binding.tvKDA.text = kdaString

            val matchDurationInSeconds = match.duration
            val minutes = (matchDurationInSeconds % 3600) / 60
            val seconds = matchDurationInSeconds % 60

            val matchLengthString: String = if (minutes < 10) {
                if (seconds < 10) {
                    "0$minutes:0$seconds"
                } else {
                    "0$minutes:$seconds"
                }
            } else {
                if (seconds < 10) {
                    "$minutes:0$seconds"
                } else {
                    "$minutes:$seconds"
                }
            }

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