package com.scottandmarc.opendotareborn.app.presentation.dashboard.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Team
import com.scottandmarc.opendotareborn.databinding.TeamListItemBinding
import com.scottandmarc.opendotareborn.toolbox.helpers.OrdinalHelper.intToOrdinal
import com.squareup.picasso.Picasso

class TeamsListAdapter(
    private val teams: List<Team>,
) : RecyclerView.Adapter<TeamsListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TeamListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val teamListItemBinding = TeamListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(teamListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (this.teams.isNotEmpty()) {
            val team: Team = teams[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            val rankString = intToOrdinal(position + 1)
            viewHolder.binding.tvTeamRank.text = rankString

            val circularProgressDrawable = CircularProgressDrawable(viewHolder.itemView.context).apply {
                strokeWidth = 5F
                centerRadius = 15F
                setColorSchemeColors(ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
                start()
            }

            if (team.logoURL == null) {
                viewHolder.binding.ivTeamLogo.setImageResource(R.drawable.ic_question_mark)
            } else {
                Picasso.get().load(team.logoURL).error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(viewHolder.binding.ivTeamLogo)
            }

            if (team.name == "") {
                val noName = "<no name>"
                viewHolder.binding.tvTeamName.text = noName
            } else {
                viewHolder.binding.tvTeamName.text = team.name
            }

            viewHolder.binding.tvTeamRating.text = team.rating.toInt().toString()

            val winLoseString = "${team.wins} - ${team.losses}"
            viewHolder.binding.tvTeamWinLose.text = winLoseString
        }
    }

    override fun getItemCount(): Int = teams.size
}