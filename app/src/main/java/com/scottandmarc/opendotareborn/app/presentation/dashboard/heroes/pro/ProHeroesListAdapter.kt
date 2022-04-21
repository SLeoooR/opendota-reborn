package com.scottandmarc.opendotareborn.app.presentation.dashboard.heroes.pro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.HeroStats
import com.scottandmarc.opendotareborn.databinding.HeroesProListItemBinding
import com.squareup.picasso.Picasso

class ProHeroesListAdapter(
    private val heroesStats: List<HeroStats>,
    private val totalProPick: Int,
) : RecyclerView.Adapter<ProHeroesListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HeroesProListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val heroesProListItemBinding = HeroesProListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(heroesProListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (this.heroesStats.isNotEmpty()) {

            val heroStats: HeroStats = heroesStats[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            Picasso.get().load("https://steamcdn-a.akamaihd.net${heroStats.img}").error(R.drawable.ic_question_mark).into(viewHolder.binding.ivHeroIcon)
            viewHolder.binding.tvHeroName.text = heroStats.localizedName


            viewHolder.binding.tvHeroPickRate.text = if (heroStats.proPick != 0) {
                String.format("%.2f", ((heroStats.proPick.toFloat() / (totalProPick / 10).toFloat()) * 100.0F)) + "%"
            } else {
                "0%"
            }
            viewHolder.binding.tvHeroBanRate.text = if (heroStats.proBan != 0) {
                String.format("%.2f", ((heroStats.proBan.toFloat() / (totalProPick / 10).toFloat()) * 100.0F)) + "%"
            } else {
                "0%"
            }
            viewHolder.binding.tvHeroWinRate.text = if (heroStats.proWin != 0) {
                String.format("%.2f", ((heroStats.proWin.toFloat() / heroStats.proPick.toFloat()) * 100.0F)) + "%"
            } else {
                "0%"
            }
        }
    }

    override fun getItemCount(): Int = heroesStats.size
}