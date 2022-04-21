package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.peers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Peer
import com.scottandmarc.opendotareborn.app.presentation.dashboard.search.SearchedPlayerActivity
import com.scottandmarc.opendotareborn.databinding.PeerListItemBinding
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.numTimeAgo
import com.squareup.picasso.Picasso

class PeersListAdapter(
    private val peers: List<Peer>,
) : RecyclerView.Adapter<PeersListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PeerListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val mPosition = layoutPosition
            val accountId: Int = peers[mPosition].accountId

            val intent = Intent(v.context, SearchedPlayerActivity::class.java)
            intent.putExtra("accountId", accountId)
            intent.putExtra("title", "Peer")
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val peerListItemBinding = PeerListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(peerListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (this.peers.isNotEmpty()) {
            val peer: Peer = peers[position]

            if (position % 2 == 0) {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_background_color))
            } else {
                viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.app_card_color))
            }

            val peerWinRate = if (peer.games != 0) {
                (peer.win.toFloat() / peer.games.toFloat()) * 100.0F
            } else {
                0.0F
            }
            val peerWinRateString = String.format("%.2f", peerWinRate) + "%"

            Picasso.get().load(peer.avatarFull).into(viewHolder.binding.ivPLayerAvatar)
            viewHolder.binding.tvPeerName.text = peer.personaname
            viewHolder.binding.tvPeerMP.text = peer.games.toString()
            viewHolder.binding.tvPeerWinRate.text = peerWinRateString
            viewHolder.binding.tvPeerLP.text = numTimeAgo(peer.lastPlayed)
        }
    }

    override fun getItemCount(): Int = peers.size
}