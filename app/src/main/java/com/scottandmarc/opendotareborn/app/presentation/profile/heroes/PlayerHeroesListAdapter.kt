package com.scottandmarc.opendotareborn.app.presentation.profile.heroes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.PlayerHero
import com.scottandmarc.opendotareborn.databinding.PlayerHeroListItemBinding
import com.scottandmarc.opendotareborn.di.DependencyInjector
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

// NeedFix
class PlayerHeroesListAdapter(
    context: Context,
    private val playerHeroesList: List<PlayerHero>,
    private val fromOverview: Boolean
) : RecyclerView.Adapter<PlayerHeroesListAdapter.ViewHolder>() {

    private val heroInfoRepository = DependencyInjector.provideHeroInfoRepository(context)

    //inner class ViewHolder(val binding: PlayerHeroListItemBinding) : RecyclerView.ViewHolder(binding.root)

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val iv: AppCompatImageView = itemView.findViewById(R.id.ivPLayerHeroIcon)
        val tv1: AppCompatTextView = itemView.findViewById(R.id.tvPlayerHeroName)
        val tv2: AppCompatTextView = itemView.findViewById(R.id.tvPlayerHeroMP)
        val tv3: AppCompatTextView = itemView.findViewById(R.id.tvPlayerHeroWR)
        val tv4: AppCompatTextView = itemView.findViewById(R.id.tvPlayerHeroLP)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        //val playerHeroListItemBinding = PlayerHeroListItemBinding.inflate(LayoutInflater.from(parent.context))

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val playerHeroItemView = inflater.inflate(R.layout.player_hero_list_item, parent, false)
        return ViewHolder(playerHeroItemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val playerHero: PlayerHero = playerHeroesList[position]
        val heroInfo = heroInfoRepository.getHeroInfoWhere(playerHero.heroId.toInt())
        val heroWinRate = if (playerHero.games != 0) {
            (playerHero.win.toFloat() / playerHero.games.toFloat()) * 100.0F
        } else {
            0.0F
        }
        val heroWinRateString = String.format("%.2f", heroWinRate) + "%"

        // val pattern = "MM-dd-yyyy HH"
        val simpleDateFormat = SimpleDateFormat("MM/dd/yy\nHH:mm", Locale.getDefault())
        // val currentDate = simpleDateFormat.format(Timestamp(System.currentTimeMillis()).time)
        val lastPlayedDate = simpleDateFormat.format(Date(playerHero.lastPlayed.toLong() * 1000))

//        Picasso.get().load("https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png").into(viewHolder.binding.ivPLayerHeroIcon)
//        viewHolder.binding.tvPlayerHeroName.text = heroInfo.localizedName
//        viewHolder.binding.tvPlayerHeroMP.text = playerHero.games.toString()
//        viewHolder.binding.tvPlayerHeroWR.text = heroWinRateString
//        viewHolder.binding.tvPlayerHeroLP.text = lastPlayedDate

        Picasso.get().load("https://steamcdn-a.akamaihd.net/apps/dota2/images/dota_react/heroes/${heroInfo.name.substring(14)}.png").into(viewHolder.iv)
        viewHolder.tv1.text = heroInfo.localizedName
        viewHolder.tv2.text = playerHero.games.toString()
        viewHolder.tv3.text = heroWinRateString
        viewHolder.tv4.text = lastPlayedDate

    }

    override fun getItemCount(): Int {
        return if (fromOverview) {
            3
        } else {
            20
        }
    }
}