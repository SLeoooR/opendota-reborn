package com.scottandmarc.opendotareborn.app.presentation.dashboard.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Search
import com.scottandmarc.opendotareborn.app.presentation.dashboard.UserDashboardActivity
import com.scottandmarc.opendotareborn.databinding.SearchListItemBinding
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.numTimeAgoUsingDate
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class SearchListAdapter(
    private val searches: List<Search>,
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SearchListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val mPosition = layoutPosition
            val accountId: Int = searches[mPosition].accountId

            val intent = Intent(v.context, SearchedPlayerActivity::class.java)
            intent.putExtra("accountId", accountId)
            intent.putExtra("title", "Searched Player")
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val searchListItemBinding = SearchListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(searchListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (this.searches.isNotEmpty()) {
            val search: Search = searches[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            Picasso.get().load(search.avatarfull).error(R.drawable.ic_question_mark).into(viewHolder.binding.ivPlayerProfilePic)

            val playerNameID = "${search.personaname} (${search.accountId})"
            viewHolder.binding.tvPlayerNameID.text = playerNameID

            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

            if (search.lastMatchTime == "") {
                viewHolder.binding.tvPlayerLastPlayed.text = ""
            } else {
                val date = formatter.parse(search.lastMatchTime!!)
                val timeAgoText = "${numTimeAgoUsingDate(date!!)} ago"
                viewHolder.binding.tvPlayerLastPlayed.text = timeAgoText
            }
        }
    }

    override fun getItemCount(): Int = searches.size
}