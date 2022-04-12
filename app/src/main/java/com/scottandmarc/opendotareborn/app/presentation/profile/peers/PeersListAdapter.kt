package com.scottandmarc.opendotareborn.app.presentation.profile.peers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.app.domain.entities.Peer
import com.scottandmarc.opendotareborn.databinding.PeerListItemBinding
import com.scottandmarc.opendotareborn.toolbox.helpers.TimeHelper.numTimeAgo
import com.squareup.picasso.Picasso

class PeersListAdapter(
    private val peers: List<Peer>,
) : RecyclerView.Adapter<PeersListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: PeerListItemBinding) : RecyclerView.ViewHolder(binding.root)

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