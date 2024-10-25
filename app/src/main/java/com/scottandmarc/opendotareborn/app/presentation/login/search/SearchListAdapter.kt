package com.scottandmarc.opendotareborn.app.presentation.login.search

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Search
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

            val intent = Intent()
            intent.putExtra("accountId", accountId)
            (v.context as Activity).setResult(RESULT_OK, intent)
            (v.context as Activity).finish()
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

            val circularProgressDrawable = CircularProgressDrawable(viewHolder.itemView.context).apply {
                strokeWidth = 5F
                centerRadius = 15F
                setColorSchemeColors(ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
                start()
            }

            Picasso.get().load(search.avatarfull).error(R.drawable.ic_question_mark).placeholder(circularProgressDrawable).into(viewHolder.binding.ivPlayerProfilePic)

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