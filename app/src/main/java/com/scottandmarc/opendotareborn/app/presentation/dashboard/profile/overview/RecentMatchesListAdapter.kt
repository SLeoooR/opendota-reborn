package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.ProcessedRecentMatch
import com.scottandmarc.opendotareborn.databinding.RecentMatchListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.squareup.picasso.Picasso

class RecentMatchesListAdapter(
    private val processedRecentMatches: List<ProcessedRecentMatch>,
) : RecyclerView.Adapter<RecentMatchesListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RecentMatchListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val recentMatchListItemBinding = RecentMatchListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(recentMatchListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val heroInfoRepository = DependencyInjector.provideHeroInfoRepository(viewHolder.binding.root.context)
        if (this.processedRecentMatches.isNotEmpty()) {
            val processedRecentMatch: ProcessedRecentMatch = processedRecentMatches[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            viewHolder.binding.tvStats.text = processedRecentMatch.stat
            viewHolder.binding.tvAverage.text = "%,d".format(processedRecentMatch.average)
            viewHolder.binding.tvMaximum.text = "%,d".format(processedRecentMatch.maximum)

            Log.d("processedMatches", processedRecentMatch.toString())

            if (processedRecentMatch.heroId != 0) {
                val heroInfo = heroInfoRepository.getHeroInfoWhere(processedRecentMatch.heroId)
                val heroIconPicURL = "https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png"
                Picasso.get().load(heroIconPicURL).into(viewHolder.binding.ivHeroIcon)
            } else {
                viewHolder.binding.ivHeroIcon.setImageResource(R.drawable.ic_question_mark)
            }
        }
    }

    override fun getItemCount(): Int = processedRecentMatches.size
}